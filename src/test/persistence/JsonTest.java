package persistence;

import model.Destination;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkDestination(String placeName, int budget, int duration, Destination destination) {
        assertEquals(placeName, destination.getPlaceName());
        assertEquals(budget, destination.getBudget());
        assertEquals(duration, destination.getDuration());
    }
}
