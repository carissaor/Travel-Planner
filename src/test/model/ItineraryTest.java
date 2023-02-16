package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItineraryTest {

    Itinerary testItinerary;
    LocalPlace testData1;
    LocalPlace testData2;

    @BeforeEach
    public void runBefore() {
        testItinerary = new Itinerary(1000, 5);
        testData1 = new LocalPlace("description", 50, 'F');
        testData2 = new LocalPlace("description", 2000, 'A');
    }

    @Test
    void testConstructor() {
        assertEquals(5, testItinerary.getItineraryList().size());
        assertEquals(1000, testItinerary.getBudgetLeft());
        assertEquals("Day 5", testItinerary.getItineraryList().get(4).getText());
    }

    @Test
    void testAddItinerary() {
        testData1.chooseThis();
        testItinerary.editItinerary(5, testData1);
        assertEquals(950, testItinerary.getBudgetLeft());
        assertEquals("description",
                testItinerary.getItineraryList().get(4).getListRelated().get(0).getDescription());
    }

    @Test
    void testAddRemoveItinerary() {
        testData1.chooseThis();
        testItinerary.editItinerary(2, testData1);
        assertEquals(950, testItinerary.getBudgetLeft());
        testData1.removeThis();
        testItinerary.editItinerary(2, testData1);
        assertEquals(1000, testItinerary.getBudgetLeft());
    }

    @Test
    void testWithinBudget() {
        assertTrue(testItinerary.withinBudget(testData1));
        assertFalse(testItinerary.withinBudget(testData2));
    }

    @Test
    void testSetBudget() {
        testItinerary.setBudget(-20);
        assertEquals(1000 - 20, testItinerary.getBudgetLeft());
        testItinerary.setBudget(-1000);
        assertEquals(0, testItinerary.getBudgetLeft());
    }

    @Test
    void testWithinDuration() {
        assertFalse(testItinerary.withinDuration(-1));
        assertTrue(testItinerary.withinDuration(5));
        assertFalse(testItinerary.withinDuration(6));
    }

    @Test
    void testSetNegativeDuration() {
        testItinerary.setDuration(-90);
        assertEquals(1, testItinerary.getDuration());
        assertEquals(1, testItinerary.getItineraryList().size());
    }

    @Test
    void testSetDuration() {
        testItinerary.setDuration(-2);
        assertEquals(3, testItinerary.getDuration());
        assertEquals(3, testItinerary.getItineraryList().size());
        testItinerary.setDuration(2);
        assertEquals(5, testItinerary.getDuration());
        assertEquals(5, testItinerary.getItineraryList().size());
        assertEquals("Day 5", testItinerary.getItineraryList().get(4).getText());
    }
}
