package ui;

import java.io.FileNotFoundException;

public class Main {
    // EFFECTS: call the App class for running the UI.
    public static void main(String[] args) {
        new Gui();
//        try {
//            new Gui();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }
    }
}
