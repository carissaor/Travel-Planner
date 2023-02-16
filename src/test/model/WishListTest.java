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
        testData1 = new LocalPlace("Steam Clock", 0, 'A');
        testData2 = new LocalPlace("Granville Island", 100, 'F');
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
    public void testGetType() {
        testData3 = new LocalPlace("Hotel", 300, 'L');
        testData4 = new LocalPlace("UBC", 100, 'O');
        testWishList.addItem(testData1);
        testWishList.addItem(testData2);
        testWishList.addItem(testData3);
        testWishList.addItem(testData4);
        assertEquals(1, testWishList.getA().size());
        assertEquals("Granville Island", testWishList.getF().get(0).getDescription());
        assertEquals(1, testWishList.getL().size());
        assertEquals("UBC", testWishList.getO().get(0).getDescription());
    }

}
