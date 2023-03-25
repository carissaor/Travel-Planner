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

public class Gui extends JFrame {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;

    private static final String JSON_STORE = "./data/app.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private DestinationList destinationList;
    private JButton rightButton;
    private JPanel bodyPanel;

    public Gui() {
        super("Trip Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initializeApp();

        splashScreen();
        loadApp();
        displayDestinations();
        saveApp();
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

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
        setLayout(new BorderLayout(10, 10));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        initializeHeader();
        initializeRight();
    }

    private void initializeRight() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightButton = new JButton("Add");
        rightButton.addActionListener(e -> addDestination());

        buttonPanel.add(rightButton, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.EAST);
    }

    // pop up window when add button is clicked
    private void addDestination() {
        JFrame frame = new JFrame("Add Destination");
        frame.setPreferredSize(new Dimension(350, 200));
        JPanel fieldsPanel = new JPanel(new GridLayout(3, 1));
        JTextField addName = createTextField("Name:", fieldsPanel);
        JTextField addBudget = createTextField("Budget:", fieldsPanel);
        JTextField addDuration = createTextField("Duration:", fieldsPanel);
        JButton addButton = new JButton("Save");
        addButton.addActionListener(e -> {
            String name = addName.getText();
            int budget = Integer.parseInt(addBudget.getText());
            int duration = Integer.parseInt(addDuration.getText());
            destinationList.addItem(new Destination(name, budget, duration));
            displayDestinations();
            frame.dispose();

        });
        frame.add(fieldsPanel, BorderLayout.CENTER);
        frame.add(buttonPanel(addButton), BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JTextField createTextField(String text, JPanel panel) {
        JTextField textField = new JTextField();
        panel.add(new JLabel(text));
        panel.add(textField);
        return textField;
    }

    private JPanel buttonPanel(JButton addButton) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        return buttonPanel;
    }

    private void displayDestinations() {
        if (bodyPanel != null) {
            remove(bodyPanel);
        }
        bodyPanel = new JPanel();
        bodyPanel.setName("bodyPanel");
        for (Destination destination : destinationList.getListRelated()) {
            JButton nameButton = new JButton(destination.getPlaceName());
            nameButton.addActionListener(e -> editDestination(destination));
            JLabel budgetLabel = new JLabel("Budget: " + destination.getBudget());
            JLabel durationLabel = new JLabel("Duration: " + destination.getDuration());
            JButton removeButton = new JButton("Remove");
            removeButton.addActionListener(e -> {
                destinationList.removeItem(destination.getPlaceName());
                displayDestinations();
            });
            bodyPanel.add(destinationPanel(nameButton, budgetLabel, durationLabel, removeButton));
        }
        setAddButton();
        add(bodyPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JPanel destinationPanel(JButton nameButton, JLabel budgetLabel, JLabel durationLabel,
                                    JButton removeButton) {
        JPanel destinationPanel = new JPanel(new GridLayout(4, 1));
        destinationPanel.add(nameButton);
        destinationPanel.add(budgetLabel);
        destinationPanel.add(durationLabel);
        destinationPanel.add(removeButton);
        return destinationPanel;
    }

    private void setAddButton() {
        rightButton.setText("Add");
        rightButton.removeActionListener(rightButton.getActionListeners()[0]);
        rightButton.addActionListener(e -> addDestination());
    }

    // edit destination page
    private void editDestination(Destination destination) {
        setHomeButton();
        remove(bodyPanel);

        bodyPanel = new JPanel(new BorderLayout());
        bodyPanel.add(basicInfoPanel(destination), BorderLayout.PAGE_START);
        bodyPanel.add(detailsPanel(destination), BorderLayout.CENTER);
        add(bodyPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private JPanel basicInfoPanel(Destination destination) {
        JPanel basicInfoPanel = new JPanel();
        JPanel placeNamePanel = new JPanel();
        JLabel infoLabel = new JLabel(destination.getPlaceName());
        infoLabel.setFont(new Font(null, Font.BOLD, 30));
        placeNamePanel.add(infoLabel);
        basicInfoPanel.add(placeNamePanel);
        basicInfoPanel.add(durationPanel(destination));
        basicInfoPanel.add(budgetPanel(destination));
        return basicInfoPanel;
    }

    private JPanel durationPanel(Destination destination) {
        String content = "Duration: ";
        JPanel durationPanel = new JPanel();
        JLabel durationLabel = new JLabel(content + destination.getDuration());
        JButton addDayButton = new JButton("+");
        addDayButton.addActionListener(e -> {
            destination.getItinerary().setDuration(1);
            durationLabel.setText(content + destination.getDuration());
            editDestination(destination);
        });

        JButton minusDayButton = new JButton("-");
        minusDayButton.addActionListener(e -> {
            EachDay day = destination.getItinerary().getItineraryList().get(destination.getItineraryList().size() - 1);
            if (day.getListRelated().size() != 0) {
                doCalculation(day, destination);
            }
            destination.getItinerary().setDuration(-1);
            durationLabel.setText(content + destination.getDuration());
            editDestination(destination);
        });

        durationPanel.add(durationLabel);
        durationPanel.add(addDayButton);
        durationPanel.add(minusDayButton);

        return durationPanel;
    }

    private void doCalculation(EachDay eachDay, Destination destination) {
        for (LocalPlace localPlace : eachDay.getListRelated()) {
            localPlace.removeThis();
            destination.getItinerary().editItinerary(eachDay.getListRelated().indexOf(localPlace) + 1, localPlace);
        }
    }

    private JPanel budgetPanel(Destination destination) {

        String content = "Budget: ";
        int budget = destination.getItinerary().getBudgetLeft();
        JPanel budgetPanel = new JPanel();
        JLabel durationLabel = new JLabel(content + budget);
        JButton addButton = new JButton("+");
        addButton.addActionListener(e -> {
            destination.getItinerary().setBudget(1);
            durationLabel.setText(content + budget);
            editDestination(destination);
        });

        JButton minusButton = new JButton("-");
        minusButton.addActionListener(e -> {
            destination.getItinerary().setBudget(-1);
            durationLabel.setText(content + budget);
            editDestination(destination);
        });

        budgetPanel.add(durationLabel);
        budgetPanel.add(addButton);
        budgetPanel.add(minusButton);

        return budgetPanel;
    }

    private JPanel detailsPanel(Destination destination) {
        JPanel wrapPanel = new JPanel(new BorderLayout());

        JPanel testing = new JPanel(new BorderLayout());
        JPanel titlePanel = new JPanel(new GridLayout(2, 0));
        JLabel wishListTitle = new JLabel("All info");
        wishListTitle.setFont(new Font(null, Font.BOLD, 20));
//        JLabel itineraryTitle = new JLabel("Itinerary");
//        itineraryTitle.setFont(new Font(null, Font.BOLD, 20));
        titlePanel.add(wishListTitle);
//        titlePanel.add(itineraryTitle);
        testing.add(titlePanel, BorderLayout.BEFORE_FIRST_LINE);

        JPanel detailsPanel = new JPanel(new GridLayout(2, 0));
        detailsPanel.add(wishListPanel(destination));
        detailsPanel.add(itineraryPanel(destination));

        wrapPanel.add(testing, BorderLayout.WEST);
        wrapPanel.add(detailsPanel, BorderLayout.CENTER);

        return wrapPanel;
    }

    private JPanel wishListPanel(Destination destination) {
        JPanel wishListPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(50, 20));
        addButton.addActionListener(e -> addWishlist(destination));
        buttonPanel.add(addButton);
        WishList wishList = destination.getWishList();
        JPanel contentPanel = new JPanel(new GridLayout(2, 2));
        for (Category category : Category.values()) {
            JPanel categoryPanel = new JPanel(new GridLayout(5, 0));
            JLabel titleLabel = new JLabel(String.valueOf(category));
            titleLabel.setFont(new Font(null, Font.BOLD, 17));
            categoryPanel.add(titleLabel);
            wishListButton(wishList, category, categoryPanel, destination);
            contentPanel.add(categoryPanel);
        }
        wishListPanel.add(contentPanel, BorderLayout.CENTER);
        wishListPanel.add(buttonPanel, BorderLayout.EAST);
        return wishListPanel;
    }

    private void wishListButton(WishList wishList, Category category, JPanel categoryPanel, Destination destination) {
        for (LocalPlace localPlace : wishList.getListRelated()) {
            if (localPlace.getCategory() == category) {
                JButton wishListButton = new JButton(localPlace.getDescription());
                wishListButton.addActionListener(e -> addToItinerary(destination, localPlace));
                categoryPanel.add(wishListButton);
            }
        }
    }

    private void addToItinerary(Destination destination, LocalPlace localPlace) {
        JFrame frame = new JFrame("Add to itinerary");
        frame.setPreferredSize(new Dimension(350, 200));
        JComboBox<Integer> dayNumBox = comboBoxDayNum(destination);
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.add(new JLabel("Which day:"));
        fieldsPanel.add(dayNumBox);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            if (!destination.getItinerary().withinBudget(localPlace)) {
                JOptionPane.showMessageDialog(frame, "Out budget");
            } else {
                int chosenDay = (int) dayNumBox.getSelectedItem();
                localPlace.chooseThis();
                destination.getItinerary().editItinerary(chosenDay, localPlace);
                editDestination(destination);
                frame.dispose();
            }
        });
        frame.add(fieldsPanel, BorderLayout.CENTER);
        frame.add(buttonPanel(addButton), BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JComboBox comboBoxDayNum(Destination destination) {
        int numDays = destination.getItinerary().getItineraryList().size();
        Integer[] dayNum = new Integer[numDays];
        for (int i = 0; i < numDays; i++) {
            dayNum[i] = i + 1;
        }
        return new JComboBox<>(dayNum);
    }

    private void addWishlist(Destination destination) {

        JFrame frame = new JFrame("Add items");
        JPanel fieldsPanel = new JPanel(new GridLayout(4, 1));
        JTextField addName = createTextField("Name: ", fieldsPanel);
        JTextField addBudget = createTextField("Budget: ", fieldsPanel);

        JComboBox categoryBox = categoryBox(fieldsPanel);
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            if (addName.getText().equals("") || addBudget.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "Please enter all info");
            } else {
                String name = addName.getText();
                int budget = Integer.parseInt(addBudget.getText());
                LocalPlace newPlace = new LocalPlace(name, budget, (Category) categoryBox.getSelectedItem());
                destination.getWishList().addItem(newPlace);
                editDestination(destination);
                frame.dispose();
            }
        });
        frame.add(fieldsPanel, BorderLayout.CENTER);
        frame.add(buttonPanel(addButton), BorderLayout.SOUTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JComboBox categoryBox(JPanel fieldsPanel) {
        Category[] category = {Category.ACTIVITIES, Category.FOODS, Category.ACCOMMODATIONS, Category.OTHERS};
        JComboBox categoryBox = new JComboBox<>(category);
        fieldsPanel.add(new JLabel("Category:"));
        fieldsPanel.add(categoryBox);
        return categoryBox;
    }

    private JPanel itineraryPanel(Destination destination) {
        JPanel itineraryPanel = new JPanel();
        itineraryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        ArrayList<EachDay> itineraryList = destination.getItineraryList();
        JPanel wrapPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel contentPanel = new JPanel(new GridLayout(0, 1));
        for (EachDay eachDay : itineraryList) {
            JPanel dayPanel = new JPanel();
            JLabel dayNum = new JLabel(eachDay.getText());
            dayNum.setFont(new Font(null, Font.BOLD, 17));
            dayPanel.add(dayNum);
            for (LocalPlace localPlace : eachDay.getListRelated()) {
                JLabel placeDetails = new JLabel();
                placeDetails.setText(localPlace.getDescription());
                dayPanel.add(placeDetails);
            }
            contentPanel.add(dayPanel);
        }
        wrapPanel.add(contentPanel, BorderLayout.CENTER);
        itineraryPanel.add(wrapPanel);
        return itineraryPanel;
    }


    private void setHomeButton() {
        rightButton.setText("Home");
        rightButton.removeActionListener(rightButton.getActionListeners()[0]);
        rightButton.addActionListener(e -> displayDestinations());
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
        int loadOption = JOptionPane.showConfirmDialog(null, "load stored data?",
                "Load App", JOptionPane.YES_NO_OPTION);
        if (loadOption == JOptionPane.YES_OPTION) {
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


