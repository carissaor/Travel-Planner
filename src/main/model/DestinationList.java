package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class DestinationList extends ListRelated<Destination> implements Writable {


    @Override
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
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
