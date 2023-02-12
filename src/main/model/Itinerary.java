package model;

import java.util.ArrayList;

public class Itinerary {

    private ArrayList<EachDay> itineraryList;
    private int budgetLeft;

    public Itinerary(int budget, int numDays) {
        this.budgetLeft = budget;
        itineraryList = new ArrayList<>(numDays);
        for (int i = 1; i <= numDays; i++) {
            itineraryList.add(new EachDay());
        }
    }

    public void addItinerary(int day, Info info, EachDay thisDay) {
        if (meetCriteria(day, info, thisDay)) {
            itineraryList.get(day).addInfo(info);
            budgetLeft -= info.getCost();
        } else if (!info.getIsChosen() && thisDay.alreadyInList(info)) {
            itineraryList.get(day).removeInfo(info);
            budgetLeft += info.getCost();
        }
    }

    private boolean meetCriteria(int day, Info info, EachDay thisDay) {
        return (day <= itineraryList.size()
                && withinBudget(info)
                && info.getIsChosen()
                && (!thisDay.alreadyInList(info)));
    }

    private boolean withinBudget(Info info) {
        if ((budgetLeft - info.getCost()) > 0) {
            budgetLeft -= info.getCost();
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<EachDay> getItineraryList() {
        return itineraryList;
    }
}

