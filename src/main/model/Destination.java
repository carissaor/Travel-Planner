package model;

import java.util.ArrayList;

public class Destination {

    private String placeName;
    private int duration;
    private int budget;
    private WishList wishList;
    private Itinerary itinerary;

    // REQUIRES: budget, duration >= 0
    // EFFECTS: construct object
    public Destination(String placeName, int budget, int duration) {
        this.placeName = placeName;
        this.budget = budget;
        this.duration = duration;
        this.wishList = new WishList();
        this.itinerary = new Itinerary(budget, duration);
    }

    public WishList getWishList() {
        return wishList;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    public ArrayList<EachDay> getItineraryList() {
        return itinerary.getItineraryList();
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getBudget() {
        return itinerary.getBudgetLeft();
    }

    public int getDuration() {
        return itinerary.getDuration();
    }
}

