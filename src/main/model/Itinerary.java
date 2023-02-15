package model;

import java.util.ArrayList;

public class Itinerary {

    private ArrayList<EachDay> itineraryList;
    private int budgetLeft;
    private int duration;
    private static final String TEXT = "Day ";

    public Itinerary(int budget, int duration) {
        this.budgetLeft = budget;
        this.duration = duration;
        itineraryList = new ArrayList<>(duration);
        for (int i = 1; i <= duration; i++) {
            itineraryList.add(new EachDay(TEXT + i));
        }
    }

    // REQUIRES: info.getCost() <= budgetLeft
    public void editItinerary(int day, Info info) {
        if (info.getIsChosen()) {
            itineraryList.get(day - 1).addItem(info);
            budgetLeft = budgetLeft - info.getCost();
        } else if (!info.getIsChosen()) {
            itineraryList.get(day - 1).removeItem(info);
            budgetLeft = budgetLeft + info.getCost();
        }
    }

    public boolean withinBudget(Info info) {
        return (budgetLeft - info.getCost()) > 0;
    }

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
            duration = 0;
        }
    }

    public int getDuration() {
        return duration;
    }

    public ArrayList<EachDay> getItineraryList() {
        return itineraryList;
    }

}

