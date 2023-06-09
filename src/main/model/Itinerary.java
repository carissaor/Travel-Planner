package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents itinerary during user's visit to a destination.
public class Itinerary implements Writable {

    private ArrayList<EachDay> itineraryList;
    private int budgetLeft;
    private int duration;
    private static final String TEXT = "Day ";

    // REQUIRES: budget >= 100, duration >= 1
    // EFFECTS: constructs an Itinerary object with initial budget, duration and
    // an array list of object EachDay with the size equals to duration.
    public Itinerary(int budget, int duration) {
        this.budgetLeft = budget;
        this.duration = duration;
        itineraryList = new ArrayList<>(duration);
        for (int i = 1; i <= duration; i++) {
            itineraryList.add(new EachDay(TEXT + i));
        }
    }

    public void setItineraryList(EachDay eachDay) {
        for (int i = 0; i < itineraryList.size(); i++) {
            if (itineraryList.get(i).getText().equals(eachDay.getText())) {
                itineraryList.set(i, eachDay);
            }
        }
    }


    // REQUIRES: 1 <= day <= duration, info.getCost() <= budgetLeft,
    // if LocalPlace IsChosen is false, the LocalPlace should be in itinerary already
    // EFFECTS: If the local place is chosen, add to itinerary according to day number and minus budget by the cost
    // Otherwise, remove it from the itinerary and add cost to budget.
    public void editItinerary(int day, LocalPlace localPlace) {
        if (localPlace.getIsChosen()) {
            itineraryList.get(day - 1).addItem(localPlace);
            budgetLeft = budgetLeft - localPlace.getCost();
        } else {
            itineraryList.get(day - 1).removeItem(localPlace);
            budgetLeft = budgetLeft + localPlace.getCost();
        }
    }

    // EFFECTS: return true if the cost of info is smaller than budgetLeft.
    public boolean withinBudget(LocalPlace localPlace) {
        return (budgetLeft - localPlace.getCost()) >= 0;
    }

    // MODIFIES: this
    // EFFECTS: add or minus budget according to amount.
    // If after adjustment, budgetLeft is a negative integer, 0 will be stored instead.
    public void setBudget(int amount) {
        budgetLeft += amount;
        if (budgetLeft < 0) {
            budgetLeft = 0;
        }
        Event event = new Event("$ " + amount + " is added to budget");
        EventLog.getInstance().logEvent(event);
    }

    public int getBudgetLeft() {
        return budgetLeft;
    }

    // EFFECTS: return true is the day number is larger than 0 and smaller than the size of itinerary list.
    //          false otherwise.
    public boolean withinDuration(int dayNum) {
        return dayNum > 0 && dayNum <= itineraryList.size();
    }

    // EFFECTS: add or minus duration by amount, add or remove EachDay object from the array list accordingly.
    //          since minimum duration is 1 day, if duration is smaller than 1, duration will be set to 1 instead.
    public void setDuration(int amount) {
        duration += amount;
        if (duration < 1) {
            duration = 1;
            for (int i = itineraryList.size() - 1; i >= 1; i--) {
                itineraryList.remove(itineraryList.get(i));
            }
        } else {
            if (amount < 0) {
                for (int i = itineraryList.size() - 1; i >= duration; i--) {
                    itineraryList.remove(itineraryList.get(i));
                    Event event = new Event(Math.abs(amount) + " day is removed from duration");
                    EventLog.getInstance().logEvent(event);
                }
            } else {
                for (int i = itineraryList.size(); i < duration; i++) {
                    itineraryList.add(new EachDay(TEXT + (i + 1)));
                    Event event = new Event(amount + " day is added to duration");
                    EventLog.getInstance().logEvent(event);
                }
            }
        }
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<EachDay> getItineraryList() {
        return itineraryList;
    }

    // EFFECTS: return JSONObject according to the itinerary object
    @Override
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("itinerary", toJsonArray());
        jo.put("budget", budgetLeft);
        jo.put("duration", duration);
        return jo;
    }

    // EFFECTS: helper function to convert itineraryList to JSONArray
    private JSONArray toJsonArray() {
        JSONArray ja = new JSONArray();

        for (EachDay d : itineraryList) {
            ja.put(d.toJson());
        }

        return ja;
    }
}

