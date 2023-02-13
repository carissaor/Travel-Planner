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
    private int duration;

    public App() {
        runApp();
    }

    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            mainMenu();
            command = userInput.next();
            command = command.toUpperCase();

            if (command.equals("Q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\n Have a good trip!");
    }

    private void init() {
        destinationList = new DestinationList();
        wishList = new WishList();
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
            System.out.println("V -> view existing destinations");
            System.out.println("Q -> quit");
        }
    }

    private void processCommand(String command) {
        if (command.equals("A")) {
            addDestination();
        } else if (command.equals("D")) {
            deleteDest();
        } else if (command.equals("V")) {
            viewDest();
        } else if (command.equals("T")) {
            addToItinerary(info);
        } else if (command.equals("M")) {
            addToWishList();
        } else if (command.equals("W")) {
            viewWishList();
        } else if (command.equals("C")) {
            chooseInfo();
        }
    }

    private void addDestination() {
        String placeName;
        int budget;

        System.out.println("Where do you want to visit? ");
        placeName = userInput.next().toLowerCase();

        System.out.println("For how many days? ");
        duration = userInput.nextInt();
        // TODO IF -ve

        System.out.println("How much budget do you have? ");
        budget = userInput.nextInt();

        destination = new Destination(placeName, duration, budget);
        itinerary = new Itinerary(duration, budget);
        destinationList.addItem(destination);

        addToWishList();
    }

    private void deleteDest() {
        String removePlace;
        System.out.println("Which destination have you visited? ");
        viewDest();
        removePlace = userInput.next().toLowerCase();
        for (Destination destination: destinationList.getList()) {
            if (removePlace.equals(destination.getPlaceName().toLowerCase())) {
                removeDestination(destination);
                break;
            }
        }
    }

    private void removeDestination(Destination destination) {
        destinationList.removeItem(destination);
    }


    private void viewDest() {
        for (Destination destination: destinationList.getList()) {
            System.out.println(destination.getPlaceName());
        }
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

        afterViewWishListMenu();
    }

    private void afterViewWishListMenu() {
        String command;
        System.out.println("Do you want to...");
        System.out.println("C -> add to itinerary");
        System.out.println("M -> add more places first");
        System.out.println("Q -> quit go back to main");
        command = userInput.next().toUpperCase();
        processCommand(command);
    }

    private void chooseInfo() {
        String chooseInfo;
        System.out.println("Which place would you like to add to itinerary?");
        chooseInfo = userInput.next().toLowerCase();
        for (Info info: wishList.getList()) {
            if (chooseInfo.equals(info.getDescription())) {
                addToItinerary(info);
            }
        }
    }

    private void addToWishList() {
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
        afterAddInfoMenu();
    }

    private void afterAddInfoMenu() {
        String command;
        System.out.println("Do you want to...");
        System.out.println("T -> add to itinerary");
        System.out.println("M -> add more places first");
        System.out.println("W -> view wishlist");
        System.out.println("Q -> quit go back to main");
        command = userInput.next().toUpperCase();
        processCommand(command);
    }

    private void addToItinerary(Info info) {
        int dayNum;
        info.toggle();
        System.out.println("Which day?");
        dayNum = userInput.nextInt();
        itinerary.addItinerary(dayNum,info);
        displayBudget();
        displayEachDay();
    }

    private void displayBudget() {
        System.out.println("you have $" + itinerary.getBudgetLeft() + " left");
    }

    private void displayEachDay() {
        System.out.println("Your Itinerary: ");
        for (int i = 0; i < itinerary.getItineraryList().size(); i++) {
            System.out.println("Day " + (i+1));
            for (int x = 0; x < itinerary.getItineraryList().get(i).getDayList().size(); x++) {
                System.out.println(itinerary.getItineraryList().get(i).getDayList().get(x).getDescription());
            }
        }
    }

    private void removeFromItinerary() {
        info.toggle();
    }

}
