package persistence;

import model.Category;
import model.Destination;
import model.DestinationList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DestinationList dl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDestinationList.json");
        try {
            DestinationList dl = reader.read();
            assertEquals(0, dl.getListRelated().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneraDestinationList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDestinationList.json");
        try {
            DestinationList dl = reader.read();
            List<Destination> destinations = dl.getListRelated();
            assertEquals(2, destinations.size());
            checkDestination("place1", 0, 1, destinations.get(0));
            checkDestination("place2", 0, 2, destinations.get(1));
            checkItinerary(0, 1, destinations.get(0).getItinerary());
            checkItinerary(0, 2, destinations.get(1).getItinerary());
            checkLocalPlace("test1", 1, Category.ACTIVITIES,
                    destinations.get(0).getWishList().getListRelated().get(0));
            checkLocalPlace("test2", 2, Category.FOODS,
                    destinations.get(1).getItinerary().getItineraryList().get(2).getListRelated().get(0));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
