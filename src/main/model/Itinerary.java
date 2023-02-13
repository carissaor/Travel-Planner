package model;

import java.util.ArrayList;

public class Itinerary {

    private ArrayList<EachDay> itineraryList;
    private int budgetLeft;

    public Itinerary(int numDays, int budget) {
        this.budgetLeft = budget;
        itineraryList = new ArrayList<>(numDays);
        for (int i = 1; i <= numDays; i++) {
            itineraryList.add(new EachDay(i));
        }
    }

    public void addItinerary(int day, Info info) {
        if (meetCriteria(day, info)) {
            itineraryList.get(day - 1).addInfo(info);
            budgetLeft = budgetLeft - info.getCost();
        } else if (!info.getIsChosen()) {
            itineraryList.get(day - 1).removeInfo(info);
            budgetLeft = budgetLeft + info.getCost();
        }
    }

    public boolean meetCriteria(int day, Info info) {
        return (day <= itineraryList.size()
                && withinBudget(info)
                && info.getIsChosen());
//                && (!thisDay.alreadyInList(info)));
    }

    private boolean withinBudget(Info info) {
        if ((budgetLeft - info.getCost()) > 0) {
            return true;
        } else {
            return false;
        }
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

