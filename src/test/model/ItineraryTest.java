package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItineraryTest {

    Itinerary testItinerary;
    Info testData1;
    Info testData2;

    @BeforeEach
    public void runBefore() {
        testItinerary = new Itinerary(5, 1000);
        testData1 = new Info("description", 50);
        testData2 = new Info("description", 2000);
    }

    @Test
    void testConstructor() {
        assertEquals(5,testItinerary.getItineraryList().size());
        assertEquals(1, testItinerary.getItineraryList().get(0).getDayNum());
        assertEquals(1000, testItinerary.getBudgetLeft());
    }

    @Test
    void testAddItinerary() {
        testData1.toggle();
        testItinerary.addItinerary(2, testData1);
        assertTrue(testItinerary.getItineraryList().get(1).alreadyInList(testData1));
        assertEquals(950, testItinerary.getBudgetLeft());
    }

    @Test
    void testOutBudget() {
        testData2.toggle();
        testItinerary.addItinerary(2, testData2);
        assertFalse(testItinerary.getItineraryList().get(1).alreadyInList(testData2));
        assertEquals(1000, testItinerary.getBudgetLeft());
    }
}
