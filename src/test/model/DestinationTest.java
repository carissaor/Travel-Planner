package model;

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
        assertEquals(3, testDest.getDuration());
    }


}