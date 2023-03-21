package ui;

import model.Destination;
import model.DestinationList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Gui extends JFrame {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;

    private static final String JSON_STORE = "./data/app.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private DestinationList destinationList;

    public Gui() {
        super("Trip Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeApp();

        splashScreen();
        loadApp();
        displayDestinations();
        saveApp();
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    // TODO progress bar
    private void splashScreen() {
        JWindow splashScreen = new JWindow();
        ImageIcon splashImage = new ImageIcon("./data/rotating_earth.gif");
        JLabel splashLabel = new JLabel();
        splashLabel.setIcon(splashImage);
        splashLabel.setHorizontalAlignment(JLabel.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(splashLabel, BorderLayout.CENTER);
        JLabel loadingLabel = new JLabel("Loading...");
        loadingLabel.setForeground(Color.white);
        panel.setBackground(Color.black);
        loadingLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(loadingLabel, BorderLayout.SOUTH);
        splashScreen.add(panel);
        splashScreen.setVisible(true);
        splashScreen.pack();
        splashScreen.setLocationRelativeTo(null);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splashScreen.dispose();
    }

    private void initializeApp() {
        destinationList = new DestinationList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        initializeHeader();
        initializeRight();
    }

    private void initializeRight() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            addDestination();
        });

        buttonPanel.add(addButton, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.EAST);
    }

    private void addDestination() {
        JFrame frame = new JFrame("Add Destination");
        frame.setPreferredSize(new Dimension(200, 200));
        JTextField addName = new JTextField();
        JTextField addBudget = new JTextField();
        JTextField addDuration = new JTextField();
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 1));
        fieldsPanel.add(new JLabel("Name:"));
        fieldsPanel.add(addName);
        fieldsPanel.add(new JLabel("Budget:"));
        fieldsPanel.add(addBudget);
        fieldsPanel.add(new JLabel("Duration:"));
        fieldsPanel.add(addDuration);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            if (addName.getText().equals("") || addBudget.getText().equals("") || addDuration.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "Please enter all info");
            } else {
                String name = addName.getText();
                int budget = Integer.parseInt(addBudget.getText());
                int duration = Integer.parseInt(addDuration.getText());
                destinationList.addItem(new Destination(name, budget, duration));
                displayDestinations();
                frame.dispose();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        frame.add(fieldsPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private void displayDestinations() {
        removeOldPanel();

        // Create a new bodyPanel with the updated list of destinations
        JPanel bodyPanel = new JPanel();
        bodyPanel.setName("bodyPanel");
        for (Destination destination : destinationList.getListRelated()) {
            JButton nameButton = new JButton(destination.getPlaceName());
            nameButton.addActionListener(e -> {
                editDestination(destination);
            });
            JLabel budgetLabel = new JLabel("Budget: " + destination.getBudget());
            JLabel durationLabel = new JLabel("Duration: " + destination.getDuration());
            JPanel destinationPanel = new JPanel(new GridLayout(3, 1));
            destinationPanel.add(nameButton);
            destinationPanel.add(budgetLabel);
            destinationPanel.add(durationLabel);
            bodyPanel.add(destinationPanel);
        }

        // Add the new bodyPanel to the JFrame and update the GUI
        add(bodyPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // edit destination page
    // TODO change the function of add button on the right,
    // TODO allow users to change duration and budget
    private void editDestination(Destination destination) {
        removeOldPanel();

        JPanel infoPanel = new JPanel();
        JLabel infoLabel = new JLabel(destination.getPlaceName());
        infoPanel.add(infoLabel);
        add(infoPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // helper function to remove old panel
    private void removeOldPanel() {
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                if (panel.getName() != null && panel.getName().equals("bodyPanel")) {
                    remove(panel);
                }
            }
        }
    }

    // Todo: pic overlap with title with title on top
    private void initializeHeader() {
        JPanel headerPanel = new JPanel();
        JLabel header = new JLabel();
        ImageIcon headerImage = new ImageIcon("./data/earth.jpeg");
        header.setIcon(headerImage);
        header.setText("Destinations");
        headerPanel.add(header);
        headerPanel.setPreferredSize(new Dimension(100, 100));
        add(headerPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: load the app from file
    private void loadApp() {
        int load = JOptionPane.showConfirmDialog(null, "load stored data?",
                "load app", JOptionPane.YES_NO_CANCEL_OPTION);
        if (load == JOptionPane.YES_OPTION) {
            try {
                destinationList = jsonReader.read();
            } catch (IOException e) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
        }
    }

    // EFFECTS: save the app to file
    private void saveApp() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                int saveOption = JOptionPane.showConfirmDialog(null,
                        "Would you like to save your file?", "Save File",
                        JOptionPane.YES_NO_OPTION);
                if (saveOption == JOptionPane.YES_OPTION) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(destinationList);
                        jsonWriter.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("Unable to write to file: " + JSON_STORE);
                    }
                }
            }
        });
    }

}


