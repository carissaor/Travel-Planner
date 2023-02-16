package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private ArrayList<Destination> destinationList;
    private Destination destination;
    private Info info;
    private Scanner userInput;

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
        destinationList = new ArrayList<>();
        userInput = new Scanner(System.in);
        userInput.useDelimiter("\n");
    }

    private void mainMenu() {
        if (destinationList.size() == 0) {
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
        } else {
            System.out.println("Invalid input");
        }
    }

    private void processCommandDest(String command) {
        if (command.equals("B")) {
            editDestInfo(true);
        } else if (command.equals("D")) {
            editDestInfo(false);
        } else if (command.equals("W")) {
            viewWishList();
        } else if (command.equals("I")) {
            editItinerary();
        } else {
            System.out.println("Invalid input");
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
        } else {
            System.out.println("Invalid input, redirect to main...");
        }
    }

    private void addDest() {
        String placeName;
        int budget;
        int duration;

        System.out.println("Where do you want to visit? ");
        placeName = userInput.next().toLowerCase();

        System.out.println("How much budget do you have? ");
        budget = userInput.nextInt();

        System.out.println("For how many days? ");
        duration = userInput.nextInt();

        destination = new Destination(placeName, budget, duration);
        destinationList.add(destination);
        addWishList();
    }

    private void deleteDest() {
        String removePlace;
        for (Destination destination : destinationList) {
            System.out.println(destination.getPlaceName());
        }
        System.out.println("Which destination have you visited? ");
        removePlace = userInput.next().toLowerCase();
        for (Destination destination : destinationList) {
            if (removePlace.equals(destination.getPlaceName().toLowerCase())) {
                destinationList.remove(destination);
                break;
            } else {
                System.out.println("no this destination");
            }
        }
    }

    private void viewDestList() {

        for (int i = 0; i < destinationList.size(); i++) {
            Destination destination = destinationList.get(i);
            System.out.println(i + 1 + ".");
            System.out.println("Place: " + destination.getPlaceName());
            System.out.println("Budget: " + destination.getBudget());
            System.out.println("Duration: " + destination.getDuration() + " days");
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
            if (destinationList.size() == 1) {
                editDest();
            } else {
                System.out.println("Which destination?");
                destName = userInput.next().toLowerCase();
                for (Destination destination : destinationList) {
                    if (destName.equals(destination.getPlaceName().toLowerCase())) {
                        this.destination = destination;
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

    private void editDestInfo(boolean isBudget) {
        System.out.println("Enter number, negative to minus amount");
        int amount = userInput.nextInt();
        if (isBudget) {
            destination.getItinerary().setBudget(amount);
            System.out.println("Budget left: " + destination.getBudget());
        } else {
            destination.getItinerary().setDuration(amount);
            System.out.println("Duration: " + destination.getDuration() + " days");
        }
    }

    private void addWishList() {
        String localPlace;
        int cost;
        char type;

        System.out.println("Where do you want to visit in " + destination.getPlaceName() + "?");
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
        destination.getWishList().addItem(info);

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
        System.out.println("ACTIVITIES");
        for (Info details: destination.getWishList().getA()) {
            System.out.println(details.getDescription());
        }
        System.out.println("FOOD");
        for (Info details: destination.getWishList().getF()) {
            System.out.println(details.getDescription());
        }
        System.out.println("LIVING SPACE");
        for (Info details: destination.getWishList().getL()) {
            System.out.println(details.getDescription());
        }
        System.out.println("OTHERS");
        for (Info details: destination.getWishList().getO()) {
            System.out.println(details.getDescription());
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
            if (!destination.getItineraryList().isEmpty()) {
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
        for (Info info : destination.getWishList().getList()) {
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
        if (destination.getItinerary().withinBudget(info)) {
            info.chooseThis();
            System.out.println("Which day?");
            dayNum = userInput.nextInt();
            while (!destination.getItinerary().withinDuration(dayNum)) {
                System.out.println("Input must be within duration, try again!");
                dayNum = userInput.nextInt();
            }
            destination.getItinerary().editItinerary(dayNum, info);
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
        destination.getItinerary().editItinerary(dayNum, info);

    }

    private void displayBudget() {
        System.out.println("you have $" + destination.getBudget() + " left");
    }

    private void displayItinerary() {
        System.out.println("Your Itinerary: ");
        for (EachDay eachDay : destination.getItineraryList()) {
            System.out.println(eachDay.getText());
            for (Info info : eachDay.getList()) {
                System.out.println(info.getDescription());
            }
        }
    }

}
