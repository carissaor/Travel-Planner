package model;

// Represents each day in the itinerary
public class EachDay extends ListRelated<Info> {

    private String text;

    // EFFECTS: creates an dayNum object with an empty array list and text stating the day number.
    public EachDay(String text) {
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
