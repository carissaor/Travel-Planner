package model;

import java.util.ArrayList;

public class ListRelated<T> {
    private ArrayList<T> list;

    public ListRelated() {
        list = new ArrayList<>();
    }

    public void addItem(T type) {
        list.add(type);
    }

    public void removeItem(T type) {
        list.remove(type);
    }

    public ArrayList<T> getList() {
        return list;
    }

}
