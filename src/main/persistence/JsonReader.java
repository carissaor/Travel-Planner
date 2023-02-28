package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DestinationList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDestinationList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses DestinationList from JSON object and returns it
    private DestinationList parseDestinationList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        DestinationList destinationList = new DestinationList(name);
        addDestinations(destinationList, jsonObject);
        return destinationList;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addDestinations(DestinationList dl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("destination list");
        for (Object json : jsonArray) {
            JSONObject nextDest = (JSONObject) json;
            addDestination(dl, nextDest);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addDestination(DestinationList dl, JSONObject jsonObject) {
        String name = jsonObject.getString("place name");
        Integer budget = jsonObject.getInt("budget");
        Integer duration = jsonObject.getInt("duration");
        Destination destination = new Destination(name, budget, duration);
        dl.addItem(destination);
    }

    private void addLocalPlace(WishList wl, JSONObject jsonObject) {
        String description = jsonObject.getString("description");
        Integer cost = jsonObject.getInt("cost");
        Category category = Category.valueOf(jsonObject.getString("category"));
        LocalPlace localPlace = new LocalPlace(description, cost, category);
        wl.addItem(localPlace);

    }

}
