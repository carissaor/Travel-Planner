package model;

public class Info {

    private String description;
    private int cost;
    private char type;
    private boolean isChosen;

    public Info(String description, int cost, char type) {
        this.description = description;
        this.cost = cost;
        this.type = type;
        this.isChosen = false;
    }
    public void toggle() {
        isChosen = !isChosen;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    public char getType() {
        return type;
    }

    public boolean getIsChosen() {
        return isChosen;
    }
}

