package model;

public class Destination {

    private String placeName;
    private int numDays;
    private int budget;
    private WishList wishList;
    private Itinerary itinerary;

    // REQUIRES: budget >= 0
    // EFFECTS:
    public Destination(String placeName, int numDays, int budget) {
        this.placeName = placeName;
        this.numDays = numDays;
        this.budget = budget;
        this.wishList = new WishList();
        this.itinerary = new Itinerary(budget, numDays);
    }

    public String getPlaceName() {
        return placeName;
    }

    public int getNumDays() {
        return numDays;
    }

    public int getBudget() {
        return budget;
    }
}

