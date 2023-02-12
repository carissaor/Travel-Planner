package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItineraryTest {

    Itinerary testItinerary;
    Info testData1;
    Info testData2;
    Info testData3;

    @BeforeEach
    public void runBefore() {
        testItinerary = new Itinerary(10, 1000);
        testData1 = new Info("Steam Clock", 0);
        testData2 = new Info("Whistler", 2000);
        testData3 = new Info("Beach", 10);
    }

//    @Test
//    void testConstructor() {
//        assertEquals(0, testItinerary.getItineraryList().size());
//    }

    @Test
    void testAddOneItinerary() {
//        testData1.toggle();
//        testItinerary.addItinerary(testData1);
//        assertEquals(1, testItinerary.getItineraryList().size());
    }

    @Test
    void testOutOfBudget() {
//        testData2.toggle();
//        testItinerary.addItinerary(testData2);
//        assertEquals(0, testItinerary.getItineraryList().size());
    }

    @Test
    void testAddMultiple() {
        //

    }
}
