package model;

import java.util.ArrayList;

public class Itinerary {

    private ArrayList<Info> itinerary;

    public Itinerary() {
        itinerary = new ArrayList<>();
    }

    public void addItinerary(Info info) {
        itinerary.add(info);
    }
}
