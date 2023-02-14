package model;

public class Destination {

    private String placeName;
    private int duration;
    private int budget;
    private WishList wishList;
    private Itinerary itinerary;

    // REQUIRES: budget, duration >= 0
    // EFFECTS:
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

    public String getPlaceName() {
        return placeName;
    }

    public int getBudget() {
        return budget;
    }

    public int getDuration() {
        return duration;
    }

}

