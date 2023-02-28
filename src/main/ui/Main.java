package ui;

import java.io.FileNotFoundException;

public class Main {
    // EFFECTS: call the App class for running the UI.
    public static void main(String[] args) {
        try {
            new App();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
