package model;

import java.util.ArrayList;

// Represent a country/ city user want to visit in the future.
public class Destination {

    private String placeName;
    private WishList wishList;
    private Itinerary itinerary;

    // REQUIRES: budget, duration >= 100
    // EFFECTS: construct object Destination with place name, empty wishlist
    //          and itinerary with budget and duration of stay.
    public Destination(String placeName, int budget, int duration) {
        this.placeName = placeName;
        this.wishList = new WishList();
        this.itinerary = new Itinerary(budget, duration);
    }

    // EFFECTS: get array list stored in itinerary.
    public ArrayList<EachDay> getItineraryList() {
        return itinerary.getItineraryList();
    }

    // EFFECTS: get the amount of current budget in itinerary.
    public int getBudget() {
        return itinerary.getBudgetLeft();
    }

    // EFFECTS: get the amount of duration of stay in itinerary.
    public int getDuration() {
        return itinerary.getDuration();
    }

    public String getPlaceName() {
        return placeName;
    }

    public WishList getWishList() {
        return wishList;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }
}

