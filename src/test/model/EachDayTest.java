package model;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EachDayTest {

    EachDay testDay;
    LocalPlace localPlace1;
    LocalPlace localPlace2;

    @BeforeEach
    public void runBefore() {
        testDay = new EachDay("Day 1");
        localPlace1 = new LocalPlace("test1", 100, Category.FOODS);
        localPlace2 = new LocalPlace("test2", 500, Category.ACTIVITIES);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testDay.getListRelated().size());
        assertEquals("Day 1", testDay.getText());
    }

    @Test
    void testAddOne() {
        testDay.addItem(localPlace1);
        assertEquals(1, testDay.getListRelated().size());
        assertEquals(localPlace1, testDay.getListRelated().get(0));
    }

    @Test
    void testAddRemove() {
        testDay.addItem(localPlace2);
        testDay.addItem(localPlace1);
        assertEquals(2, testDay.getListRelated().size());
        assertEquals(localPlace2, testDay.getListRelated().get(0));
        testDay.removeItem(localPlace2);
        assertEquals(localPlace1, testDay.getListRelated().get(0));
    }

    @Test
    void testToJson() {
        testDay.addItem(localPlace1);
        testDay.addItem(localPlace2);
        JSONObject testData = testDay.toJson();
        assertEquals("Day 1", testData.getString("description"));
        assertEquals(2, testData.getJSONArray("each day").length());
    }

}


