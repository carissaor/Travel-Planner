package model;

import java.util.ArrayList;

public class WishList {

    private ArrayList<Info> wishlist;

    public WishList() {
        wishlist = new ArrayList<>();
    }

    public void addInfo(Info info) {
        wishlist.add(info);
    }

}
