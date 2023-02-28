package model;

import org.json.JSONObject;
import persistence.Writable;

// Represent a place/ activity in destination
public class LocalPlace implements Writable {

    private String description;
    private int cost;
    private Category category;
    private boolean isChosen;

    // EFFECTS: creates an Info object with place name, cost, category
    // and by default it is not added to itinerary.
    public LocalPlace(String description, int cost, Category category) {
        this.description = description;
        this.cost = cost;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public boolean getIsChosen() {
        return isChosen;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("description", description);
        jo.put("cost", cost);
        jo.put("category", category);
        jo.put("is chosen", isChosen);
        return jo;
    }

}

