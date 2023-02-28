package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class WishListTest {

    WishList testWishList;
    LocalPlace testData1;
    LocalPlace testData2;
    LocalPlace testData3;
    LocalPlace testData4;


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

}
