package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EachDayTest {

    EachDay testDay;
    Info info1;
    Info info2;

    @BeforeEach
    public void runBefore() {
        testDay = new EachDay("Day 1");
        info1 = new Info("test1", 100, 'A');
    }

    @Test
    void testConstructor() {
        assertEquals(0, testDay.getList().size());
        assertEquals("Day 1", testDay.getDayNum());
    }

    @Test
    void testAddOne() {
        testDay.addItem(info1);
        assertEquals(1, testDay.getList().size());
        assertEquals(info1, testDay.getList().get(0));
    }

    @Test
    void testAddRemove() {
        info2 = new Info("test2", 500, 'O');
        testDay.addItem(info2);
        testDay.addItem(info1);
        assertEquals(2, testDay.getList().size());
        assertEquals(info2, testDay.getList().get(0));
        testDay.removeItem(info2);
        assertEquals(info1, testDay.getList().get(0));
    }

}


