package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DestinationTest {

    Destination testDest;

    @BeforeEach
    public void runBefore() {
        testDest = new Destination("Vancouver", 1000, 10);
    }

    @Test
    void testConstructor() {
        assertEquals("Vancouver", testDest.getPlaceName());
        assertEquals(1000, testDest.getBudget());
        assertEquals(10, testDest.getDuration());
        assertEquals(0, testDest.getWishList().getListRelated().size());
        assertEquals(10, testDest.getItinerary().getItineraryList().size());
    }

    @Test
    void testGetItineraryList() {
        assertEquals(10, testDest.getItineraryList().size());
        assertEquals("Day 1", testDest.getItineraryList().get(0).getText());
    }

    @Test
    void testGetBudget() {
        assertEquals(1000, testDest.getBudget());
    }

    @Test
    void testGetDuration() {
        assertEquals(10, testDest.getDuration());
    }

    @Test
    void testToJson() {
        JSONObject testData = testDest.toJson();
        assertEquals(testDest.getPlaceName(), testData.getString("place name"));
        assertEquals(testDest.getBudget(), testData.getInt("budget"));
        assertEquals(testDest.getDuration(), testData.getInt("duration"));
        assertEquals(testDest.getWishList().getListRelated().size(), testData.getJSONArray("wishlist").length());
        assertEquals(testDest.getItineraryList().size(), testData.getJSONArray("itinerary").length());
    }
}