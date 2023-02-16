package model;

import java.util.ArrayList;

public class ListRelated<T> {
    public ArrayList<T> list;

    // EFFECTS: construct object with empty array list
    public ListRelated() {
        list = new ArrayList<>();
    }

    // REQUIRES: type not in list
    public void addItem(T type) {
        list.add(type);
    }

    // REQUIRES: type in list
    public void removeItem(T type) {
        list.remove(type);
    }

    public ArrayList<T> getList() {
        return list;
    }


}

