package pharmacysystem;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import pharmacysystem.Pharmacy;

public class PharmacyGUI extends JFrame implements ActionListener {
    private JButton pharmacyButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private PharmacyPanel pharmacyPanel;
    

    public PharmacyGUI() {
        super("Pharmacy Management");

        pharmacyButton = new JButton("View Pharmacy");
        pharmacyButton.addActionListener(this);


        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel.add(pharmacyButton);

        pharmacyPanel = new PharmacyPanel();

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(pharmacyPanel, "PharmacyPanel");

        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pharmacyButton) {
            pharmacyPanel.initialize();
            cardLayout.show(mainPanel, "PharmacyPanel");
        } 
    }

    public static void main(String[] args) {
        PharmacyGUI gui = new PharmacyGUI();
    }

    private class PharmacyPanel extends JPanel {

    	private JLabel pharmacyNameLabel;
        private JTextField pharmacyNameField;
        private JLabel websiteURLLabel;
        private JTextField websiteURLField;
        private JLabel ownerLabel;
        private JTextField ownerField;
        private JLabel phoneNumberLabel;
        private JTextField phoneNumberField;
        private JLabel hoursofOperationLabel;
        private JTextField hoursofOperationField;
        private JLabel socialMediaLabel;
        private JTextField socialMediaField;
        private JButton saveButton;
        private Pharmacy pharmacy;
        
        public PharmacyPanel() {
            pharmacy = new Pharmacy("Yuma Pharmacy", "http://yumapharmacy.com", "Joe Yuma", "928-123-4567", "5:00 am - 5:00 pm", "@yumapharmacy");

            // Initialize the UI components
            pharmacyNameLabel = new JLabel("Pharmacy Name: ");
            pharmacyNameField = new JTextField();
            websiteURLLabel = new JLabel("Website URL:");
            websiteURLField = new JTextField();
            ownerLabel = new JLabel("Owner:");
            ownerField = new JTextField();
            phoneNumberLabel = new JLabel("Phone Number:");
            phoneNumberField = new JTextField();
            hoursofOperationLabel = new JLabel("Hours of Operation:");
            hoursofOperationField = new JTextField();
            socialMediaLabel = new JLabel("Social Media Handle:");
            socialMediaField = new JTextField();
            saveButton = new JButton("Save");

            saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == saveButton) {
                    Pharmacy updatedPharmacy = getUpdatedPharmacy(); // Implement this method to create a new Pharmacy object with the updated values
                    PharmacyPanel updatedPanel = new PharmacyPanel();
                    updatedPanel.pharmacy = updatedPharmacy;
                }
            }
        });

            // Add the UI components to the frame
            setLayout(new GridLayout(7, 2));
            add(pharmacyNameLabel);
            add(pharmacyNameField);
            add(websiteURLLabel);
            add(websiteURLField);
            add(ownerLabel);
            add(ownerField);
            add(phoneNumberLabel);
            add(phoneNumberField);
            add(hoursofOperationLabel);
            add(hoursofOperationField);
            add(socialMediaLabel);
            add(socialMediaField);
            add(new JLabel());
            add(saveButton);

        }

        public void initialize() {
        pharmacyNameField.setText(pharmacy.getName());
                websiteURLField.setText(pharmacy.getWebsiteUrl());
                ownerField.setText(pharmacy.getOwner());
                phoneNumberField.setText(pharmacy.getPhoneNumber());
                hoursofOperationField.setText(pharmacy.getHoursOfOperation());
                socialMediaField.setText(pharmacy.getSocialMediaHandle());
        }
            

            private Pharmacy getUpdatedPharmacy() {
                // Implement this method to create a new Pharmacy object with the updated values
                String name = pharmacyNameField.getText();
                String websiteUrl = websiteURLField.getText();
                String owner = ownerField.getText();
                String phoneNumber = phoneNumberField.getText();
                String hoursOfOperation = hoursofOperationField.getText();
                String socialMediaHandle = socialMediaField.getText();
                Pharmacy updatedPharmacy = new Pharmacy(name, websiteUrl, owner, phoneNumber, hoursOfOperation, socialMediaHandle);
                return updatedPharmacy;
            }
        }
    }