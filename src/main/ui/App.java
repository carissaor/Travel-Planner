package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private Destination destination;
    private DestinationList destinationList;
    private Info info;
    private Itinerary itinerary;
    private Scanner userInput;
    private int numDays;

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

        System.out.println("\nGoodbye!");
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
            System.out.println("V -> view existing destinations");
            System.out.println("Q -> quit");
        }
    }

    private void processCommand(String command) {
        if (command.equals("A")) {
            addDestination();
        } else if (command.equals("D")) {
            canRemove();
        } else if (command.equals("V")) {
            viewDestination();
        } else if (command.equals("T")) {
            addToItinerary();
        } else if (command.equals("M")) {
            addInfo();
        }
    }

    private void addDestination() {
        String placeName;
        int budget;

        System.out.println("Where do you want to visit? ");
        placeName = userInput.next().toLowerCase();

        System.out.println("For how many days? ");
        numDays = userInput.nextInt();
        // TODO IF -ve

        System.out.println("How much budget do you have? ");
        budget = userInput.nextInt();

        destination = new Destination(placeName, numDays, budget);
        itinerary = new Itinerary(numDays, budget);
        destinationList.addItem(destination);

        addInfo();
    }

    private void canRemove() {
        String removePlace;
        System.out.println("Which destination have you visited? ");
        viewDestination();
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


    private void viewDestination() {
        for (Destination destination: destinationList.getList()) {
            System.out.println(destination.getPlaceName());
        }
    }

    private void addInfo() {
        String localPlace;
        int cost;

        System.out.println("Where do you want to visit?");
        localPlace = userInput.next();

        System.out.println("What is the estimated cost?");
        cost = userInput.nextInt();

        info = new Info(localPlace, cost);

        afterAddInfoMenu();
    }

    private void afterAddInfoMenu() {
        String command;
        System.out.println("Do you want to...");
        System.out.println("T -> add to itinerary");
        System.out.println("M -> add more places first");
        System.out.println("Q -> quit");
        command = userInput.next();
        command.toUpperCase();
        processCommand(command);
    }

    private void addToItinerary() {
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
