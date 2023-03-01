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
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDestinationList.json");
        try {
            DestinationList dl = reader.read();
            List<Destination> destinations = dl.getListRelated();
            assertEquals(2, destinations.size());
            checkDestination("place1", 100, 1, destinations.get(0));
            checkDestination("place2", 200, 2, destinations.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
