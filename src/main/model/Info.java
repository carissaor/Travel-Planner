package model;

public class Info {

    private String description;
    private int cost;
    private boolean isChosen;

    public Info(String description, int cost) {
        this.description = description;
        this.cost = cost;
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

    public boolean getIsChosen() {
        return isChosen;
    }
}

