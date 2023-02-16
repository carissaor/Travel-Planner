package model;

import java.util.ArrayList;

// Represents itinerary during user's visit to a destination.
public class Itinerary {

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

    // REQUIRES: 1 <= day <= duration, info.getCost() <= budgetLeft
    // EFFECTS:
    public void editItinerary(int day, Info info) {
        if (info.getIsChosen()) {
            itineraryList.get(day - 1).addItem(info);
            budgetLeft = budgetLeft - info.getCost();
        } else if (!info.getIsChosen()) {
            itineraryList.get(day - 1).removeItem(info);
            budgetLeft = budgetLeft + info.getCost();
        }
    }

    // EFFECTS: return true if the cost of info is smaller than budgetLeft.
    public boolean withinBudget(Info info) {
        return (budgetLeft - info.getCost()) > 0;
    }

    // MODIFIES: this
    // EFFECTS: add or minus budget according to amount.
    // If after adjustment, budgetLeft is a negative integer, 0 will be stored instead.
    public void setBudget(int amount) {
        budgetLeft += amount;
        if (budgetLeft < 0) {
            budgetLeft = 0;
        }
    }

    public int getBudgetLeft() {
        return budgetLeft;
    }

    public boolean withinDuration(int day) {
        return day > 0 && day <= itineraryList.size();
    }

    public void setDuration(int amount) {
        duration += amount;
        if (duration < 0) {
            duration = 1;
        } else {
            if (amount < 0) {
                for (int i = itineraryList.size() - 1; i >= duration; i--) {
                    itineraryList.remove(itineraryList.get(i));
                }
            } else {
                for (int i = itineraryList.size(); i < duration; i++) {
                    itineraryList.add(new EachDay(TEXT + (i + 1)));
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

}

