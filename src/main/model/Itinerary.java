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

    public void editItinerary(int day, Info info) {
        if (info.getIsChosen()) {
            itineraryList.get(day - 1).addItem(info);
            budgetLeft = budgetLeft - info.getCost();
        } else  {
            itineraryList.get(day - 1).removeItem(info);
            budgetLeft = budgetLeft + info.getCost();
        }
    }

    public boolean withinDuration(int day) {
        return day > 0 && day <= itineraryList.size();
    }

    public void setDuration(int amount) {
        duration += amount;
    }

    public int getDuration() {
        return duration;
    }

    public boolean withinBudget(Info info) {
        return (budgetLeft - info.getCost()) > 0;
    }

    public void setBudgetLeft(int amount) {
        budgetLeft += amount;
    }

    public int getBudgetLeft() {
        return budgetLeft;
    }

    public ArrayList<EachDay> getItineraryList() {
        return itineraryList;
    }
}

