package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocalPlaceTest {

    LocalPlace testLocalPlace;

    @BeforeEach
    public void runBefore() {
        testLocalPlace = new LocalPlace("ubc", 100, Category.ACTIVITIES);
    }

    @Test
    void testConstructor() {
        assertEquals("ubc", testLocalPlace.getDescription());
        assertEquals(100, testLocalPlace.getCost());
        assertEquals(Category.ACTIVITIES, testLocalPlace.getCategory());
        assertFalse(testLocalPlace.getIsChosen());
    }

    @Test
    void testToggle() {
        testLocalPlace.chooseThis();
        assertTrue(testLocalPlace.getIsChosen());
        testLocalPlace.removeThis();
        assertFalse(testLocalPlace.getIsChosen());
    }

    @Test
    void testToJson() {
        JSONObject testData = testLocalPlace.toJson();
        assertEquals("ubc", testData.getString("description"));
        assertEquals(100, testData.getInt("cost"));
        assertEquals(Category.ACTIVITIES, testData.get("category"));
        assertFalse(testData.getBoolean("is chosen"));
    }

}
