package model;

public class Destination {

    private String placeName;
    private int numDays;
    private int budget;
    private WishList wishList = new WishList();
    private Itinerary itinerary = new Itinerary(numDays, budget);

    // REQUIRES: budget >= 0
    // EFFECTS:
    public Destination(String placeName, int numDays, int budget) {
//                       WishList wishList, Itinerary itinerary) {
        this.placeName = placeName;
        this.numDays = numDays;
        this.budget = budget;
//        this.wishList = wishList;
//        this.itinerary = itinerary;
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

