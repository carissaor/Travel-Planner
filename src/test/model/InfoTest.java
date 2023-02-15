package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfoTest {

    Info testInfo;

    @BeforeEach
    public void runBefore() {
        testInfo = new Info("ubc", 100, 'A');
    }

    @Test
    void testConstructor() {
        assertEquals("ubc", testInfo.getDescription());
        assertEquals(100, testInfo.getCost());
        assertEquals('A', testInfo.getType());
        assertFalse(testInfo.getIsChosen());
    }

    @Test
    void testToggle() {
        testInfo.chooseThis();
        assertTrue(testInfo.getIsChosen());
        testInfo.removeThis();
        assertFalse(testInfo.getIsChosen());
    }

}
