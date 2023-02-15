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
        testItinerary = new Itinerary(1000, 5);
        testData1 = new Info("description", 50, 'F');
        testData2 = new Info("description", 2000, 'A');
    }

    @Test
    void testConstructor() {
        assertEquals(5, testItinerary.getItineraryList().size());
        assertEquals(1000, testItinerary.getBudgetLeft());
        assertEquals("Day 1", testItinerary.getItineraryList().get(0).getDayNum());
    }

    @Test
    void testAddItinerary() {
        testData1.chooseThis();
        testItinerary.editItinerary(2, testData1);
        assertEquals(950, testItinerary.getBudgetLeft());
    }


    @Test
    void testSetBudget() {
        testItinerary.setBudget(-20);
        assertEquals(1000 - 20, testItinerary.getBudgetLeft());
    }

    @Test
    void testSetOutBudget() {
        testItinerary.setBudget(-2000);
        assertEquals(0, testItinerary.getBudgetLeft());
    }

    @Test
    void testWithinBudget() {
        assertTrue(testItinerary.withinBudget(testData1));
        assertFalse(testItinerary.withinBudget(testData2));
    }
}
