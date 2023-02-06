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
    public void chooseThis() {
        isChosen = true;
    }

    public void removeThis() {
        isChosen = false;
    }

    public void addToItinerary(Itinerary itinerary) {
        if (isChosen == true) {
            itinerary.addItinerary(this);
        }
    }
}
