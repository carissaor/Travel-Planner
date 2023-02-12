package model;

import java.util.ArrayList;

public class EachDay {

    private int dayNum;
    private ArrayList<Info> dayList;

    public EachDay(int dayNum) {
        this.dayNum = dayNum;
        dayList = new ArrayList<>();
    }

    public void addInfo(Info info) {
        if (!alreadyInList(info)) {
            dayList.add(info);
        }
    }

    // REQUIRES: info already in this.
    // MODIFIES: this
    // EFFECTS: remove info from daylist.
    public void removeInfo(Info info) {
        if (alreadyInList(info)) {
            dayList.remove(info);
        }
    }

    // EFFECTS: return true if info is already added to itinerary
    public boolean alreadyInList(Info info) {
        return dayList.contains(info);
    }

    public ArrayList<Info> getDayList() {
        return dayList;
    }

    public int getDayNum() {
        return dayNum;
    }
}
