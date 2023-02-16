package model;

import java.util.ArrayList;

// Represent the information researched by users regarding a particular destination
public class WishList extends ListRelated<Info> {

    // EFFECTS: return an array list containing information in the activities category
    public ArrayList<Info> getA() {
        ArrayList<Info> infoA = new ArrayList<>();
        for (Info info : super.getListRelated()) {
            if (info.getType() == 'A') {
                infoA.add(info);
            }
        }
        return infoA;
    }

    // EFFECTS: return an array list containing information in the food category
    public ArrayList<Info> getF() {
        ArrayList<Info> infoF = new ArrayList<>();
        for (Info info : super.getListRelated()) {
            if (info.getType() == 'F') {
                infoF.add(info);
            }
        }
        return infoF;
    }

    // EFFECTS: return an array list containing information in the living space category
    public ArrayList<Info> getL() {
        ArrayList<Info> infoL = new ArrayList<>();
        for (Info info : super.getListRelated()) {
            if (info.getType() == 'L') {
                infoL.add(info);
            }
        }
        return infoL;
    }

    // EFFECTS: return an array list containing information in the others category
    public ArrayList<Info> getO() {
        ArrayList<Info> infoO = new ArrayList<>();
        for (Info info : super.getListRelated()) {
            if (info.getType() == 'O') {
                infoO.add(info);
            }
        }
        return infoO;
    }
}




