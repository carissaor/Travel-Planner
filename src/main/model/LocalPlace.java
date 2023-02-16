package model;

// Represent a place/ activity in destination
public class LocalPlace {

    private String description;
    private int cost;
    private char type;
    private boolean isChosen;

    // EFFECTS: creates an Info object with place name, cost, category
    // and by default it is not added to itinerary.
    public LocalPlace(String description, int cost, char type) {
        this.description = description;
        this.cost = cost;
        this.type = type;
        this.isChosen = false;
    }

    // MODIFIES: this
    // EFFECTS: choose this object to add to itinerary.
    public void chooseThis() {
        isChosen = true;
    }

    // REQUIRES: this object is in the array list of EachDay object.
    // MODIFIES: this
    // EFFECTS: remove this object from the itinerary.
    public void removeThis() {
        isChosen = false;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public char getType() {
        return type;
    }

    public boolean getIsChosen() {
        return isChosen;
    }
}

