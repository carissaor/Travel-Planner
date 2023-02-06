package model;

import java.util.ArrayList;

public class DestinationList {
    private ArrayList<Destination> destinations;

    public DestinationList() {
        destinations = new ArrayList<>();
    }

    public void addPlace(Destination destination) {
        destinations.add(destination);
    }

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }
}
