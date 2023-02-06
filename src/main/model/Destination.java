package model;

public class Destination {

    private String palce;
    private int budget;
    private WishList wishList;
    private Itinerary itinerary;

    // REQUIRES: budget >= 0
    // EFFECTS:
    public Destination(String place, int budget,
                       WishList wishList, Itinerary itinerary) {
        this.palce = place;
        this.budget = budget;
        this.wishList = wishList;
        this.itinerary = itinerary;
    }

    public String getPalce() {
        return palce;
    }

    public int getBudget() {
        return budget;
    }

}
