package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represents each day in the itinerary
public class EachDay extends ListRelated<LocalPlace> implements Writable {

    private String text;

    // EFFECTS: creates an dayNum object with an empty array list and text stating the day number.
    public EachDay(String text) {
        super();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("description", text);
        jo.put("each day", toJsonArray());
        return jo;
    }

    private JSONArray toJsonArray() {
        JSONArray ja = new JSONArray();

        for (LocalPlace p : super.getListRelated()) {
            ja.put(p.toJson());
        }

        return ja;
    }
}
