package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represent a country/ city user want to visit in the future.
public class Destination implements Writable {

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

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public void setItinerary(Itinerary itinerary) {
        this.itinerary = itinerary;
    }

    public WishList getWishList() {
        return wishList;
    }

    public Itinerary getItinerary() {
        return itinerary;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("place name", placeName);
        jo.put("budget", getBudget());
        jo.put("duration", getDuration());
        jo.put("wishlist", toJsonArrayWishList());
        jo.put("itinerary", toJsonArrayItinerary());
        return jo;
    }

    private JSONArray toJsonArrayItinerary() {
        JSONArray ja = new JSONArray();
        for (EachDay ed : itinerary.getItineraryList()) {
            ja.put(ed.toJson());
        }
        return ja;
    }

    public JSONArray toJsonArrayWishList() {

        JSONArray ja = new JSONArray();
        for (LocalPlace localPlace : wishList.getListRelated()) {
            ja.put(localPlace.toJson());
        }
        return ja;
    }
}

