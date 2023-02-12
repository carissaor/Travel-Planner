package model;

public class Info {

    private String description;
    private int cost;
    private int dayNum;
    private boolean isChosen;

    public Info(String description, int cost) {
        this.description = description;
        this.cost = cost;
        this.dayNum = 0;
        this.isChosen = false;
    }
    public void toggle() {
        isChosen = !isChosen;
    }

    public void setDayNum(int dayNum) {
        this.dayNum = dayNum;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public int getDayNum() {
        return dayNum;
    }

    public boolean getIsChosen() {
        return isChosen;
    }
}

