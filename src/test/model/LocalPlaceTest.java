package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocalPlaceTest {

    LocalPlace testLocalPlace;

    @BeforeEach
    public void runBefore() {
        testLocalPlace = new LocalPlace("ubc", 100, 'A');
    }

    @Test
    void testConstructor() {
        assertEquals("ubc", testLocalPlace.getDescription());
        assertEquals(100, testLocalPlace.getCost());
        assertEquals('A', testLocalPlace.getType());
        assertFalse(testLocalPlace.getIsChosen());
    }

    @Test
    void testToggle() {
        testLocalPlace.chooseThis();
        assertTrue(testLocalPlace.getIsChosen());
        testLocalPlace.removeThis();
        assertFalse(testLocalPlace.getIsChosen());
    }

}
