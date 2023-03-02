package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DestinationListTest {

    DestinationList destinationList;
    Destination destination1;
    Destination destination2;

    @BeforeEach
    public void runBefore() {
        destinationList = new DestinationList();
        destination1 = new Destination("place1", 100, 3);
        destination2 = new Destination("place2", 300, 5);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, destinationList.getListRelated().size());
    }

    @Test
    public void testToJson() {
        destinationList.addItem(destination1);
        destinationList.addItem(destination2);
        JSONObject testData = destinationList.toJson();
        assertEquals("2", testData.getJSONArray("destination list").length());
    }

}
