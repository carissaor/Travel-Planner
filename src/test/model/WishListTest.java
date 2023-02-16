package model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class WishListTest {

    WishList testWishList;
    Info testData1;
    Info testData2;
    Info testData3;
    Info testData4;


    @BeforeEach
    public void runBefore() {
        testWishList = new WishList();
        testData1 = new Info("Steam Clock", 0, 'A');
        testData2 = new Info("Granville Island", 100, 'F');
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

    @Test
    public void testGetType() {
        testData3 = new Info("Hotel", 0, 'L');
        testData4 = new Info("UBC", 100, 'O');
        testWishList.addItem(testData1);
        testWishList.addItem(testData2);
        testWishList.addItem(testData3);
        testWishList.removeItem(testData3);
        testWishList.addItem(testData4);
        assertEquals(1,testWishList.getA().size());
        assertEquals("Granville Island",testWishList.getF().get(0).getDescription());
        assertEquals(0, testWishList.getL().size());
        assertEquals("UBC",testWishList.getO().get(0).getDescription());
    }

}
