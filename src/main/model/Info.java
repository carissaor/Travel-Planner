package model;

public class Info {

    private String description;
    private int cost;
    private char type;
    private boolean isChosen;

    // EFFECTS: creates an Info object with place name, cost and category.
    public Info(String description, int cost, char type) {
        this.description = description;
        this.cost = cost;
        this.type = type;
        this.isChosen = false;
    }

    public void chooseThis() {
        isChosen = true;
    }

    public void removeThis() {
        isChosen = false;
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

