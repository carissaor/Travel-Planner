package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Represent a travel planner application
public class App {

    private static final String JSON_STORE = "./data/app.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private DestinationList destinationList;
    private Destination destination;
    private LocalPlace localPlace;
    private Scanner userInput;

    // EFFECTS: run the application
    public App() throws FileNotFoundException {

        userInput = new Scanner(System.in);
        destinationList = new DestinationList("user's");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command;

//        init();
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

    // MODIFIES: this
    // EFFECTS: initialize a list of destination to empty
//    private void init() {
//        destinationList = new ArrayList<>();
//        userInput = new Scanner(System.in);
//        userInput.useDelimiter("\n");
//    }

    // EFFECTS: prints main menu
    private void mainMenu() {
//        if (destinationList.getListRelated().size() == 0) {
//            System.out.println("A -> add new destination");
//            System.out.println("Q -> quit");
//        } else {
            System.out.println("A -> add new destination");
            System.out.println("D -> delete an existing destination");
            System.out.println("V -> view existing destination(s)");
            System.out.println("L -> load saved data");
            System.out.println("S -> save");
            System.out.println("Q -> quit");
//        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input in main level
    private void processCommandMain(String command) {
        if (command.equals("A")) {
            addDest();
        } else if (command.equals("D")) {
            deleteDest();
        } else if (command.equals("V")) {
            viewDestList();
        } else if (command.equals("L")) {
            loadApp();
        } else if (command.equals("S")) {
            saveApp();
        } else {
            System.out.println("Invalid input");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input in destination level
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
            System.out.println("Invalid input, redirect to main...");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user input in wishlist level
    private void processCommandWL(String command) {
        if (command.equals("A")) {
            addToItinerary(localPlace);
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

    // MODIFIES: this
    // EFFECTS: add a destination to destination list
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
        destinationList.getListRelated().add(destination);
        addWishList();
    }

    // MODIFIES: this
    // EFFECTS: remove a destination to destination list
    private void deleteDest() {
        String removePlace;
        ArrayList<Destination> dl = destinationList.getListRelated();

        for (Destination destination : dl) {
            System.out.println(destination.getPlaceName());
        }
        System.out.println("Which destination have you visited? ");
        removePlace = userInput.next().toLowerCase();
        for (Destination destination : dl) {
            if (removePlace.equals(destination.getPlaceName().toLowerCase())) {
                dl.remove(destination);
                break;
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    // EFFECTS: prints the destination list
    private void viewDestList() {

        ArrayList<Destination> dl = destinationList.getListRelated();

        for (int i = 0; i < dl.size(); i++) {
            Destination destination = dl.get(i);
            System.out.println(i + 1 + ".");
            System.out.println("Place: " + destination.getPlaceName());
            System.out.println("Budget: " + destination.getBudget());
            System.out.println("Duration: " + destination.getDuration() + " days");
        }
        afterViewDestMenu();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void afterViewDestMenu() {
        String command;
        String destName;
        ArrayList<Destination> dl = destinationList.getListRelated();

        System.out.println("E -> edit destination info");
        System.out.println("Q -> quit");
        command = userInput.next().toUpperCase();
        if (command.equals("E")) {
            if (dl.size() == 1) {
                editDest();
            } else {
                System.out.println("Which destination?");
                destName = userInput.next().toLowerCase();
                for (Destination destination : dl) {
                    if (destName.equals(destination.getPlaceName().toLowerCase())) {
                        this.destination = destination;
                        editDest();
                        break;
                    }
                }
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user input
    private void editDest() {
        String command;
        System.out.println("B -> budget");
        System.out.println("D -> duration");
        System.out.println("W -> wishlist");
        System.out.println("I -> Itinerary");
        command = userInput.next().toUpperCase();
        processCommandDest(command);
    }

    // MODIFIES: this
    // EFFECTS: change and print amendments in budget or duration
    private void editDestInfo(boolean isBudget) {
        System.out.println("Enter amount: ");
        int amount = userInput.nextInt();
        if (isBudget) {
            destination.getItinerary().setBudget(amount);
            System.out.println("Budget left: " + destination.getBudget());
        } else {
            destination.getItinerary().setDuration(amount);
            System.out.println("Duration: " + destination.getDuration() + " days");
        }
    }

    // MODIFIES: this
    // EFFECTS: add a LocalPlace object to wishlist
    private void addWishList() {
        String localPlace;
        int cost;

        System.out.println("Where do you want to visit in " + destination.getPlaceName() + "?");
        localPlace = userInput.next();

        System.out.println("What is the estimated cost?");
        cost = userInput.nextInt();

        Category category = readCategory();
//        System.out.println("Which category?");
//        System.out.println("A -> activity");
//        System.out.println("F -> food");
//        System.out.println("L -> living space");
//        System.out.println("O -> others");
//        String typeTemp = userInput.next().toUpperCase();
//        type = typeTemp.charAt(0);

        this.localPlace = new LocalPlace(localPlace, cost, category);
        destination.getWishList().addItem(this.localPlace);

        afterAddWL();
    }

    private Category readCategory() {
        System.out.println("Please select a category for your thingy");

        int menuLabel = 1;
        for (Category c : Category.values()) {
            System.out.println(menuLabel + ": " + c);
            menuLabel++;
        }

        int menuSelection = userInput.nextInt();
        return Category.values()[menuSelection - 1];
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void afterAddWL() {
        String command;
        System.out.println("Do you want to...");
        System.out.println("A -> add this to itinerary");
        System.out.println("M -> add more places to wishlist");
        System.out.println("V -> view wish list");
        command = userInput.next().toUpperCase();
        processCommandWL(command);
    }

    private void viewWishList() {
        ArrayList<LocalPlace> wishList = destination.getWishList().getListRelated();

        for (LocalPlace localPlace : wishList) {
            System.out.println(localPlace.getDescription());
        }

    }

    // EFFECTS: display wishlist according to category and call function to display menu afterwards
//    private void viewWishList() {
//        WishList wishList = destination.getWishList();
//
//        System.out.println("ACTIVITIES");
//        for (LocalPlace details : wishList.getA()) {
//            System.out.println(details.getDescription());
//        }
//        System.out.println("");
//        System.out.println("FOOD");
//        for (LocalPlace details : wishList.getF()) {
//            System.out.println(details.getDescription());
//        }
//        System.out.println("");
//        System.out.println("LIVING SPACE");
//        for (LocalPlace details : wishList.getL()) {
//            System.out.println(details.getDescription());
//        }
//        System.out.println("");
//        System.out.println("OTHERS");
//        for (LocalPlace details : wishList.getO()) {
//            System.out.println(details.getDescription());
//        }
//        afterViewWL();
//    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void afterViewWL() {
        String command;
        System.out.println("");
        System.out.println("Do you want to...");
        System.out.println("E -> edit itinerary");
        System.out.println("M -> add more places first");
        System.out.println("V -> view wishlist");
        command = userInput.next().toUpperCase();
        processCommandWL(command);
    }

    // EFFECTS: display current wish list according to category
//    private void displayWishList() {
//
//        WishList wishList = destination.getWishList();
//
//        System.out.println("ACTIVITIES");
//        for (LocalPlace details : wishList.getA()) {
//            System.out.println(details.getDescription());
//        }
//        System.out.println("");
//        System.out.println("FOOD");
//        for (LocalPlace details : wishList.getF()) {
//            System.out.println(details.getDescription());
//        }
//        System.out.println("");
//        System.out.println("LIVING SPACE");
//        for (LocalPlace details : wishList.getL()) {
//            System.out.println(details.getDescription());
//        }
//        System.out.println("");
//        System.out.println("OTHERS");
//        for (LocalPlace details : wishList.getO()) {
//            System.out.println(details.getDescription());
//        }
//    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void editItinerary() {
        String chooseInfo;
        System.out.println("A -> add place to itinerary");
        System.out.println("R -> remove place from itinerary");
        System.out.println("V -> view itinerary");
        String input = userInput.next().toUpperCase();
        if (input.equals("A")) {
            viewWishList();
            System.out.println("Which place would you like to add to itinerary?");
            chooseInfo = userInput.next().toLowerCase();
            editingItinerary(chooseInfo, true);
        } else if (input.equals("R")) {
            displayItinerary();
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

    // EFFECTS: choose operation on itinerary
    private void editingItinerary(String chooseInfo, boolean isAdd) {
        for (LocalPlace localPlace : destination.getWishList().getListRelated()) {
            if (chooseInfo.equals(localPlace.getDescription().toLowerCase())) {
                if (isAdd) {
                    addToItinerary(localPlace);
                } else {
                    removeFromItinerary(localPlace);
                }
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: add a LocalPlace object to itinerary
    private void addToItinerary(LocalPlace localPlace) {
        int dayNum;
        if (destination.getItinerary().withinBudget(localPlace)) {
            localPlace.chooseThis();
            System.out.println("Which day?");
            dayNum = userInput.nextInt();
            while (!destination.getItinerary().withinDuration(dayNum)) {
                System.out.println("Input must be within duration, try again!");
                dayNum = userInput.nextInt();
            }
            destination.getItinerary().editItinerary(dayNum, localPlace);
        } else {
            System.out.println("Out budget!");
        }
        displayBudget();
        displayItinerary();
    }

    // MODIFIES: this
    // EFFECTS: remove a LocalPlace object from itinerary
    private void removeFromItinerary(LocalPlace localPlace) {
        int dayNum;
        System.out.println("Which day? ");
        dayNum = userInput.nextInt();

        for (LocalPlace place : destination.getItineraryList().get(dayNum - 1).getListRelated()) {
            if (place.getDescription().equals(localPlace.getDescription())) {
                localPlace.removeThis();
                destination.getItinerary().editItinerary(dayNum, localPlace);
                break;
            }
        }
    }

    // EFFECTS: prints the current budget left
    private void displayBudget() {
        System.out.println("you have $" + destination.getBudget() + " left");
    }

    // EFFECTS: prints the itinerary
    private void displayItinerary() {
        System.out.println("Your Itinerary: ");
        for (EachDay eachDay : destination.getItineraryList()) {
            System.out.println(eachDay.getText());
            for (LocalPlace localPlace : eachDay.getListRelated()) {
                System.out.println(localPlace.getDescription());
            }
        }
    }

    // EFFECTS: save the destination list to file
    private void saveApp() {
        try {
            jsonWriter.open();
            jsonWriter.write(destinationList);
            jsonWriter.close();
            System.out.println("Saved " + destinationList.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

//     MODIFIES: this
//     EFFECTS: loads workroom from file
    private void loadApp() {
        try {
            destinationList = jsonReader.read();
            System.out.println("Loaded " + destinationList.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}
