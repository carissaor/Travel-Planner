package model;

import java.util.ArrayList;

public class WishList extends ListRelated<Info> {

    public ArrayList<Info> getA() {
        ArrayList<Info> infoA = new ArrayList<>();
        for (Info info : list) {
            if (info.getType() == 'A') {
                infoA.add(info);
            }
        }
        return infoA;
    }

    public ArrayList<Info> getF() {
        ArrayList<Info> infoF = new ArrayList<>();
        for (Info info : list) {
            if (info.getType() == 'F') {
                infoF.add(info);
            }
        }
        return infoF;
    }

    public ArrayList<Info> getL() {
        ArrayList<Info> infoL = new ArrayList<>();
        for (Info info : list) {
            if (info.getType() == 'L') {
                infoL.add(info);
            }
        }
        return infoL;
    }

    public ArrayList<Info> getO() {
        ArrayList<Info> infoO = new ArrayList<>();
        for (Info info : list) {
            if (info.getType() == 'O') {
                infoO.add(info);
            }
        }
        return infoO;
    }
}




