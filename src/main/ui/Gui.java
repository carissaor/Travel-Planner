package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// TODO place everything, (filter destination), (progress bar),
//  JList for wishlist and itinerary
public class Gui extends JFrame {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;

    private static final String JSON_STORE = "./data/app.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private DestinationList destinationList;
    private JButton rightButton;

    public Gui() {
        super("Trip Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initializeApp();

//        splashScreen();
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
        rightButton = new JButton("Add");
        rightButton.addActionListener(e -> {
            addDestination();
        });

        buttonPanel.add(rightButton, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.EAST);
    }

    // pop up window when add button is clicked
    private void addDestination() {
        JFrame frame = new JFrame("Add Destination");
        frame.setPreferredSize(new Dimension(350, 200));
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
        JButton addButton = new JButton("Save");
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
        frame.setLocationRelativeTo(null);
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
            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                destinationList.removeItem(destination.getPlaceName());
                displayDestinations();
            });
            JPanel destinationPanel = new JPanel(new GridLayout(4, 1));
            destinationPanel.add(nameButton);
            destinationPanel.add(budgetLabel);
            destinationPanel.add(durationLabel);
            destinationPanel.add(removeButton);
            bodyPanel.add(destinationPanel);
        }

        rightButton.setText("Add");
        rightButton.removeActionListener(rightButton.getActionListeners()[0]);
        rightButton.addActionListener(e -> {
            addDestination();
        });

        add(bodyPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // edit destination page
    private void editDestination(Destination destination) {
        setHomeButton();
        removeOldPanel();
        JLabel infoLabel = new JLabel(destination.getPlaceName());
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        infoPanel.setName("info");
        infoPanel.add(infoLabel);
        infoPanel.add(durationPanel(destination));
        infoPanel.add(wishListPanel(destination));
        infoPanel.add(itineraryPanel(destination));
        add(infoPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JPanel durationPanel(Destination destination) {
        String content = "Duration: ";
        JPanel durationPanel = new JPanel();
        JLabel durationLabel = new JLabel(content + destination.getDuration());
        JButton addDayButton = new JButton("+");
        addDayButton.addActionListener(e -> {
            destination.getItinerary().setDuration(1);
            durationLabel.setText(content + destination.getDuration());
        });

        JButton minusDayButton = new JButton("-");
        minusDayButton.addActionListener(e -> {
            destination.getItinerary().setDuration(-1);
            durationLabel.setText(content + destination.getDuration());
        });

        durationPanel.add(durationLabel);
        durationPanel.add(addDayButton);
        durationPanel.add(minusDayButton);

        return durationPanel;
    }

    private JPanel wishListPanel(Destination destination) {
        JPanel wishListPanel = new JPanel();
        WishList wishList = destination.getWishList();
        JLabel wishListLabel = new JLabel();
        for (LocalPlace localPlace : wishList.getListRelated()) {
            if (localPlace.getCategory() == Category.ACTIVITIES) {
                wishListLabel.setText(localPlace.getDescription());
            }
        }
        wishListPanel.add(wishListLabel);
        return wishListPanel;
    }

    private JPanel itineraryPanel(Destination destination) {
        JPanel itineraryPanel = new JPanel();
        ArrayList<EachDay> itineraryList = destination.getItineraryList();
        for (EachDay eachDay : itineraryList) {
            JLabel dayNum = new JLabel(eachDay.getText());
            JLabel placeDetails = new JLabel();
            itineraryPanel.add(dayNum);
            if (eachDay.getListRelated().size() > 0) {
                placeDetails.setText(eachDay.getListRelated().get(itineraryList.indexOf(eachDay)).getDescription());
                itineraryPanel.add(placeDetails);
            }
        }
        return itineraryPanel;
    }

    private void setHomeButton() {
        rightButton.setText("Home");
        rightButton.removeActionListener(rightButton.getActionListeners()[0]);
        rightButton.addActionListener(e -> {
            displayDestinations();
        });
    }

    // helper function to remove old panel
    private void removeOldPanel() {
        Component[] components = getContentPane().getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                if (panel.getName() != null && (panel.getName().equals("bodyPanel") ||
                        panel.getName().equals("info"))) {
                    remove(panel);
                }
            }
        }
    }

    private void initializeHeader() {
        JLayeredPane headerPanel = new JLayeredPane();
        ImageIcon headerImage = new ImageIcon("./data/earth.jpeg");
        JLabel headerImg = new JLabel(headerImage);
        headerImg.setBounds(0, 0, headerImage.getIconWidth(), 100);
        JLabel headerText = new JLabel("Destinations", SwingConstants.LEFT);
        headerText.setForeground(Color.WHITE);
        headerText.setFont(new Font(null, Font.BOLD, 30));
        headerText.setBounds(20, 20, headerImage.getIconWidth(), 100);
        headerPanel.add(headerImg, Integer.valueOf(0));
        headerPanel.add(headerText, Integer.valueOf(1));
        headerPanel.setPreferredSize(new Dimension(headerImage.getIconWidth(), 100));
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


