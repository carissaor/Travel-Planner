package model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ListRelatedTest {

    WishList testWishList;
    Info testData1;
    Info testData2;

    @BeforeEach
    public void runBefore() {
        testWishList = new WishList();
        testData1 = new Info("Steam Clock", 0);
        testData2 = new Info("Granville Island", 100);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testWishList.getList().size());
    }

    @Test
    public void testAddOne() {
        testWishList.addItem(testData1);
        assertEquals(1, testWishList.getList().size());
    }

    @Test
    public void testAddMultiple() {
        testWishList.addItem(testData1);
        testWishList.addItem(testData2);
        assertEquals(2, testWishList.getList().size());
    }

    @Test
    public void testRemove() {
        testWishList.addItem(testData1);
        testWishList.addItem(testData2);
        testWishList.removeItem(testData2);
        assertEquals(1, testWishList.getList().size());
    }
}
