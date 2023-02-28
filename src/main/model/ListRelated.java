package model;

import org.json.JSONArray;

import java.util.ArrayList;

// A subclass for WishList and EachDay as the two classes both have an array list and similar behavior
public class ListRelated<T> {
    private ArrayList<T> listRelated;

    // EFFECTS: construct ListRelated object with empty array list
    public ListRelated() {
        listRelated = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add type to array list
    public void addItem(T type) {
        listRelated.add(type);
    }

    // REQUIRES: type is in the array list
    // MODIFIES: this
    // EFFECTS: remove type from the array list
    public void removeItem(T type) {
        listRelated.remove(type);
    }

    public ArrayList<T> getListRelated() {
        return listRelated;
    }


}

