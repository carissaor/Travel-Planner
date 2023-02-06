package model;

public class Destination {

    private String palce;
    private int budget;
    private Wishlist wishlist;
    private Itinerary itinerary;

    // REQUIRES: budget >= 0
    // EFFECTS:
    public Destination(String place, int budget,
                       Wishlist wishlist, Itinerary itinerary) {
        this.palce = place;
        this.budget = budget;
        this.wishlist = wishlist;
        this.itinerary = itinerary;
    }

    public String getPalce() {
        return palce;
    }

    public int getBudget() {
        return budget;
    }

}
