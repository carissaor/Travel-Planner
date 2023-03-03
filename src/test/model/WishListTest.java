package model;

import org.json.JSONObject;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

// Represent tests for WishList class
public class WishListTest {

    WishList testWishList;
    LocalPlace testData1;
    LocalPlace testData2;

    @BeforeEach
    public void runBefore() {
        testWishList = new WishList();
        testData1 = new LocalPlace("Steam Clock", 0, Category.ACTIVITIES);
        testData2 = new LocalPlace("Granville Island", 100, Category.FOODS);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testWishList.getListRelated().size());
    }

    @Test
    public void testAddOne() {
        testWishList.addItem(testData1);
        assertEquals(1, testWishList.getListRelated().size());
    }

    @Test
    public void testAddMultiple() {
        testWishList.addItem(testData1);
        testWishList.addItem(testData2);
        assertEquals(2, testWishList.getListRelated().size());
    }

    @Test
    public void testRemove() {
        testWishList.addItem(testData1);
        testWishList.addItem(testData2);
        testWishList.removeItem(testData2);
        assertEquals(1, testWishList.getListRelated().size());
    }

    @Test
    public void testToJson() {
        testWishList.addItem(testData1);
        testWishList.addItem(testData2);
        JSONObject testData = testWishList.toJson();
        assertEquals(2, testData.getJSONArray("wishlist").length());
    }

}
