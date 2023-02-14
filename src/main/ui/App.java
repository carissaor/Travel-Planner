package ui;

import model.*;

import java.util.Scanner;

public class App {

    private Destination destination;
    private DestinationList destinationList;
    private WishList wishList;
    private Info info;
    private Itinerary itinerary;
    private Scanner userInput;
    private int budget;
    private int duration;

    public App() {
        runApp();
    }

    private void runApp() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            mainMenu();
            command = userInput.next();
            command = command.toUpperCase();

            if (command.equals("Q")) {
                keepGoing = false;
            } else {
                processCommandMain(command);
            }
        }

        System.out.println("Have a good trip!");
    }

    private void init() {
        destinationList = new DestinationList();
        userInput = new Scanner(System.in);
        userInput.useDelimiter("\n");
    }

    private void mainMenu() {
        if (destinationList.getList().size() == 0) {
            System.out.println("A -> add new destination");
            System.out.println("Q -> quit");
        } else {
            System.out.println("A -> add new destination");
            System.out.println("D -> delete an existing destination");
            System.out.println("V -> view existing destination(s)");
            System.out.println("Q -> quit");
        }
    }

    private void processCommandMain(String command) {
        if (command.equals("A")) {
            addDest();
        } else if (command.equals("D")) {
            deleteDest();
        } else if (command.equals("V")) {
            viewDestList();
        }
    }

    private void processCommandDest(String command) {
        int amount;
        if (command.equals("B")) {
            System.out.println("How much?");
            amount = userInput.nextInt();
            itinerary.setBudgetLeft(amount);
            System.out.println("Remaining budget: " + itinerary.getBudgetLeft());
        } else if (command.equals("D")) {
            System.out.println("How much?");
            amount = userInput.nextInt();
            itinerary.setDuration(amount);
            System.out.println("New duration: " + itinerary.getDuration());
        } else if (command.equals("W")) {
            viewWishList();
        } else if (command.equals("I")) {
            editItinerary();
        } else {
            System.out.println("no this choice");
        }
    }

    private void processCommandWL(String command) {
        if (command.equals("A")) {
            addToItinerary(info);
        } else if (command.equals("M")) {
            addWishList();
        } else if (command.equals("V")) {
            viewWishList();
        } else if (command.equals("E")) {
            editItinerary();
        }
    }

    private void addDest() {
        String placeName;

        System.out.println("Where do you want to visit? ");
        placeName = userInput.next().toLowerCase();

        System.out.println("How much budget do you have? ");
        budget = userInput.nextInt();

        System.out.println("For how many days? ");
        duration = userInput.nextInt();

        destination = new Destination(placeName, budget, duration);
        destinationList.addItem(destination);
        wishList = new WishList();
        itinerary = new Itinerary(budget, duration);

        addWishList();
    }

    private void deleteDest() {
        String removePlace;
        for (Destination destination: destinationList.getList()) {
            System.out.println(destination.getPlaceName());
        }
        System.out.println("Which destination have you visited? ");
        removePlace = userInput.next().toLowerCase();
        for (Destination destination: destinationList.getList()) {
            if (removePlace.equals(destination.getPlaceName().toLowerCase())) {
                destinationList.removeItem(destination);
                break;
            } else {
                System.out.println("no this destination");
            }
        }
    }

    private void viewDestList() {

        for (int i = 0; i < destinationList.getList().size(); i++) {
            Destination destination = destinationList.getList().get(i);
            System.out.println(i + 1 + ".");
            System.out.println("Place: " + destination.getPlaceName());
            System.out.println("Budget: " + destination.getItinerary().getBudgetLeft());
            System.out.println("Duration: " + destination.getItinerary().getDuration() + " days");
        }
        afterViewDestMenu();
    }

    private void afterViewDestMenu() {
        String command;
        String destName;
        System.out.println("E -> edit destination info");
        System.out.println("Q -> quit");
        command = userInput.next().toUpperCase();
        if (command.equals("E")) {
            if (destinationList.getList().size() == 1) {
                editDest();
            } else {
                System.out.println("Which destination?");
                destName = userInput.next().toLowerCase();
                for (Destination destination : destinationList.getList()) {
                    if (destName.equals(destination.getPlaceName().toLowerCase())) {
                        this.destination = destination;
                        this.budget = destination.getItinerary().getBudgetLeft();
                        this.wishList = destination.getWishList();
                        this.itinerary = destination.getItinerary();
                        editDest();
                        break;
                    }
                }
            }
        }
    }

    private void editDest() {
        String command;
        System.out.println("B -> budget");
        System.out.println("D -> duration");
        System.out.println("W -> wishlist");
        System.out.println("I -> Itinerary");
        command = userInput.next().toUpperCase();
        processCommandDest(command);
    }

    private void addWishList() {
        String localPlace;
        int cost;
        char type;

        System.out.println("Where do you want to visit?");
        localPlace = userInput.next();

        System.out.println("What is the estimated cost?");
        cost = userInput.nextInt();

        System.out.println("Which category?");
        System.out.println("A -> activity");
        System.out.println("F -> food");
        System.out.println("L -> living space");
        System.out.println("O -> others");
        String typeTemp = userInput.next().toUpperCase();
        type = typeTemp.charAt(0);

        info = new Info(localPlace, cost, type);
        wishList.addItem(info);

        afterAddWL();
    }

    private void afterAddWL() {
        String command;
        System.out.println("Do you want to...");
        System.out.println("A -> add this to itinerary");
        System.out.println("M -> add more places to wishlist");
        System.out.println("V -> view wish list");
        System.out.println("Q -> quit go back to main");
        command = userInput.next().toUpperCase();
        processCommandWL(command);
    }

    private void viewWishList() {
        for (Info info : wishList.getList()) {
            if (info.getType() == 'A') {
                System.out.println("ACTIVITIES");
                System.out.println(info.getDescription());
            } else if (info.getType() == 'F') {
                System.out.println("FOOD");
                System.out.println(info.getDescription());
            } else if (info.getType() == 'L') {
                System.out.println("LIVING SPACE");
                System.out.println(info.getDescription());
            } else {
                System.out.println("OTHERS");
                System.out.println(info.getDescription());
            }
        }

        afterViewWL();
    }

    private void afterViewWL() {
        String command;
        System.out.println("Do you want to...");
        System.out.println("E -> edit itinerary");
        System.out.println("M -> add more places first");
        System.out.println("V -> view wishlist");
        System.out.println("Q -> quit go back to main");
        command = userInput.next().toUpperCase();
        processCommandWL(command);
    }

    private void editItinerary() {
        String chooseInfo;
        System.out.println("A -> add place to itinerary");
        System.out.println("R -> remove place from itinerary");
        System.out.println("V -> view itinerary");
        String input = userInput.next().toUpperCase();
        if (input.equals("A")) {
            System.out.println("Which place would you like to add to itinerary?");
            chooseInfo = userInput.next().toLowerCase();
            editingItinerary(chooseInfo, true);
        } else if (input.equals("R")) {
            if (!itinerary.getItineraryList().isEmpty()) {
                System.out.println("type place name to remove");
                chooseInfo = userInput.next().toLowerCase();
                editingItinerary(chooseInfo, false);
            } else {
                System.out.println("nothing to remove");
            }
        } else if (input.equals("V")) {
            displayItinerary();
        }
    }

    private void editingItinerary(String chooseInfo, boolean isAdd) {
        for (Info info: wishList.getList()) {
            if (chooseInfo.equals(info.getDescription().toLowerCase())) {
                if (isAdd) {
                    addToItinerary(info);
                } else {
                    removeFromItinerary(info);
                }
            }
        }
    }


    private void addToItinerary(Info info) {
        int dayNum;
        if (itinerary.withinBudget(info)) {
            info.chooseThis();
            System.out.println("Which day?");
            dayNum = userInput.nextInt();
            while (!itinerary.withinDuration(dayNum)) {
                System.out.println("Input must be within duration, try again!");
                dayNum = userInput.nextInt();
            }
            itinerary.editItinerary(dayNum, info);
        } else {
            System.out.println("out budget!");
        }
        displayBudget();
        displayItinerary();
    }

    private void removeFromItinerary(Info info) {
        int dayNum;
        System.out.println("From which day? ");
        dayNum = userInput.nextInt();
        itinerary.editItinerary(dayNum, info);

    }

    private void displayBudget() {
        System.out.println("you have $" + itinerary.getBudgetLeft() + " left");
    }

    private void displayItinerary() {
        System.out.println("Your Itinerary: ");
        for (EachDay eachDay : itinerary.getItineraryList()) {
            System.out.println(eachDay.getDayNum());
            for (Info info : eachDay.getList()) {
                System.out.println(info.getDescription());
            }
        }
    }

}
