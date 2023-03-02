package persistence;

import model.Category;
import model.Destination;
import model.Itinerary;
import model.LocalPlace;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkDestination(String placeName, int budget, int duration, Destination destination) {
        assertEquals(placeName, destination.getPlaceName());
        assertEquals(budget, destination.getBudget());
        assertEquals(duration, destination.getDuration());
    }

    protected void checkItinerary(int budget, int duration, Itinerary itinerary) {
        assertEquals(budget, itinerary.getBudgetLeft());
        assertEquals(duration, itinerary.getDuration());
    }

    protected void checkLocalPlace(String description, int cost, Category category, LocalPlace localPlace) {
        assertEquals(description, localPlace.getDescription());
        assertEquals(cost, localPlace.getCost());
        assertEquals(category, localPlace.getCategory());
    }
}
