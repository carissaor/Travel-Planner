package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

// represent the list of destination
public class DestinationList extends ListRelated<Destination> implements Writable {

    // EFFECTS: remove destination from destinationList if the name equals
    public void removeItem(String name) {
        super.getListRelated().removeIf(destination -> {
            if (destination.getPlaceName().equals(name)) {
                Event event = new Event("remove " + destination.getPlaceName() + " from destination list.");
                EventLog.getInstance().logEvent(event);
                return true;
            }
            return false;
        });
    }

    @Override
    public void addItem(Destination destination) {
        super.getListRelated().add(destination);
        Event event = new Event("add " + destination.getPlaceName() + " to destination list.");
        EventLog.getInstance().logEvent(event);

    }

    // EFFECTS: return JSONObject according to the destination list object
    @Override
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("destination list", toJsonArray());
        return jo;
    }

    // EFFECTS: helper function to convert destinationList to JsonArray
    private JSONArray toJsonArray() {
        JSONArray ja = new JSONArray();
        for (Destination d : super.getListRelated()) {
            ja.put(d.toJson());
        }
        return ja;
    }
}
