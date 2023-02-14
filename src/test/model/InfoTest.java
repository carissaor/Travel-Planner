package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoTest {

    Info testInfo;

    @BeforeEach
    public void runBefore() {
        testInfo = new Info("Granville Island", 100, 'A');
    }

    @Test
    void testConstructor() {
        assertEquals("Granville Island", testInfo.getDescription());
        assertEquals(100, testInfo.getCost());
        assertFalse(testInfo.getIsChosen());
    }

    @Test
    void addAndRemoveFromItinerary() {
        testInfo.chooseThis();
        assertTrue(testInfo.getIsChosen());
        testInfo.removeThis();
        assertTrue(testInfo.getIsChosen());
    }

}
