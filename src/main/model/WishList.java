package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// Represent the information researched by users regarding a particular destination
public class WishList extends ListRelated<LocalPlace> implements Writable {

    @Override
    public JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("wishlist", toJsonArray());
        return jo;
    }

    public JSONArray toJsonArray() {
        JSONArray ja = new JSONArray();
        for (LocalPlace wl : super.getListRelated()) {
            ja.put(wl.toJson());
        }
        return ja;
    }


}




