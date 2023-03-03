package persistence;

import model.Destination;
import model.DestinationList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Represent tests for JsonWriter class
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            DestinationList dl = new DestinationList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            DestinationList dl = new DestinationList();
            JsonWriter writer = new JsonWriter("./data/testWriteEmptyDestinationList.json");
            writer.open();
            writer.write(dl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriteEmptyDestinationList.json");
            dl = reader.read();
            assertEquals(0, dl.getListRelated().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            DestinationList dl = new DestinationList();
            dl.getListRelated().add(new Destination("place1", 100, 1));
            dl.getListRelated().add(new Destination("place2", 200, 2));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDestinationList.json");
            writer.open();
            writer.write(dl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDestinationList.json");
            dl = reader.read();
            List<Destination> destinations = dl.getListRelated();
            assertEquals(2, destinations.size());
            checkDestination("place1", 100, 1, destinations.get(0));
            checkDestination("place2", 200, 2, destinations.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}