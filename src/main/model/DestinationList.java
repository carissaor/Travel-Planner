package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

public class DestinationList extends ListRelated<Destination> implements Writable {

    private String name;

    public DestinationList(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("name", name);
        jo.put("destination list", toJsonArray());
        return jo;
    }

    private JSONArray toJsonArray() {
        JSONArray ja = new JSONArray();
        for (Destination d : super.getListRelated()) {
            ja.put(d.toJson());
        }
        return ja;
    }
}
