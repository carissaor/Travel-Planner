package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EachDayTest {

    EachDay testDay;
    Info info1;
    Info info2;

    @BeforeEach
    public void runBefore() {
        testDay = new EachDay(1);
        info1 = new Info("test1", 100, 'A');
    }

    @Test
    void testConstructor() {
        assertEquals(0, testDay.getDayList().size());
    }

    @Test
    void testAddInfo() {
        info2 = new Info("test2", 0, 'A');
        testDay.addInfo(info1);
        assertEquals(1, testDay.getDayList().size());
        testDay.addInfo(info2);
        assertEquals(2, testDay.getDayList().size());
        assertEquals(info2, testDay.getDayList().get(1));
        assertEquals("test1", testDay.getDayList().get(0).getDescription());
    }

    @Test
    void testRemoveInfo() {
        testDay.addInfo(info1);
        testDay.removeInfo(info1);
        assertEquals(0, testDay.getDayList().size());
    }

    @Test
    void testAddRemoveMultiple() {
        info2 = new Info("test2", 0, 'A');
        testDay.addInfo(info1);
        testDay.addInfo(info2);
        testDay.removeInfo(info2);
        assertEquals(1, testDay.getDayList().size());
    }
}

