package model;

import java.util.ArrayList;

public class EachDay extends ListRelated<Info> {

    private String dayNum;

    // EFFECTS: creates an dayNum object with an empty array list and dayNum
    public EachDay(String dayNum) {
        super();
        this.dayNum = dayNum;
    }

    public String getDayNum() {
        return dayNum;
    }
}
