package model;

import java.util.ArrayList;

// Represent the information researched by users regarding a particular destination
public class WishList extends ListRelated<LocalPlace> {

    // EFFECTS: return an array list containing information in the activities category
    public ArrayList<LocalPlace> getA() {
        ArrayList<LocalPlace> localPlaceA = new ArrayList<>();
        for (LocalPlace localPlace : super.getListRelated()) {
            if (localPlace.getType() == 'A') {
                localPlaceA.add(localPlace);
            }
        }
        return localPlaceA;
    }

    // EFFECTS: return an array list containing information in the food category
    public ArrayList<LocalPlace> getF() {
        ArrayList<LocalPlace> localPlaceF = new ArrayList<>();
        for (LocalPlace localPlace : super.getListRelated()) {
            if (localPlace.getType() == 'F') {
                localPlaceF.add(localPlace);
            }
        }
        return localPlaceF;
    }

    // EFFECTS: return an array list containing information in the living space category
    public ArrayList<LocalPlace> getL() {
        ArrayList<LocalPlace> localPlaceL = new ArrayList<>();
        for (LocalPlace localPlace : super.getListRelated()) {
            if (localPlace.getType() == 'L') {
                localPlaceL.add(localPlace);
            }
        }
        return localPlaceL;
    }

    // EFFECTS: return an array list containing information in the others category
    public ArrayList<LocalPlace> getO() {
        ArrayList<LocalPlace> localPlaceO = new ArrayList<>();
        for (LocalPlace localPlace : super.getListRelated()) {
            if (localPlace.getType() == 'O') {
                localPlaceO.add(localPlace);
            }
        }
        return localPlaceO;
    }
}




