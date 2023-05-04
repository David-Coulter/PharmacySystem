package pharmacysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PharmacyGUI extends JFrame implements ActionListener {
    private JButton pharmacyButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private PharmacyPanel pharmacyPanel;
    private JPanel headerPanel;
    Font myFont = new Font("Proxima Nova", Font.BOLD, 14);
    

    public PharmacyGUI() {
        super("Pharmacy Management");
        ImageIcon img = new ImageIcon("C:\\Users\\Coulter\\Desktop\\PharmacySystem_Team1\\PharmacySystem\\src\\resources\\ua.png");
            this.setIconImage(img.getImage());
        // set up the header panel with an image
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/pharmacy.png"));
        JLabel header = new JLabel(icon);
        header.setPreferredSize(new Dimension(200, 50));
        headerPanel = new JPanel();
        headerPanel.add(header);
        headerPanel.setBackground(new Color(171, 5, 32));

        pharmacyButton = new JButton("View Pharmacy");
        pharmacyButton.setFocusable(false);
        pharmacyButton.setFont(myFont);
        pharmacyButton.addActionListener(this);
        pharmacyButton.setForeground(Color.WHITE);
        pharmacyButton.setBackground(new Color(12, 35, 75));


        JPanel buttonPanel = new JPanel(new GridLayout(1, 1));
        buttonPanel.setPreferredSize(new Dimension(500, 30));
        buttonPanel.add(pharmacyButton);

        
        Pharmacy pharmacy = new Pharmacy("SFWE Pharmacy", "http://sfwepharmacy.com", "Sharon Oneal", "520-123-4567", "5:00 am - 5:00 pm", "@sfwepharmacy");
        pharmacyPanel = new PharmacyPanel(pharmacy);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(pharmacyPanel, "PharmacyPanel");
        mainPanel.setPreferredSize(new Dimension(600, 420));

        //Use the same layout and add the same padding and gap values
        add(headerPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        setLocationRelativeTo(null);
        
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
        private JButton resetButton;
        private Pharmacy pharmacy;
        
        
        public PharmacyPanel(Pharmacy pharmacy) {
            this.pharmacy = pharmacy;
        
            // Initialize the UI components
            pharmacyNameLabel = new JLabel("Pharmacy Name: ");
            pharmacyNameField = new JTextField(pharmacy.getName());
            websiteURLLabel = new JLabel("Website URL:");
            websiteURLField = new JTextField(pharmacy.getWebsiteUrl());
            ownerLabel = new JLabel("Owner:");
            ownerField = new JTextField(pharmacy.getOwner());
            phoneNumberLabel = new JLabel("Phone Number:");
            phoneNumberField = new JTextField(pharmacy.getPhoneNumber());
            hoursofOperationLabel = new JLabel("Hours of Operation:");
            hoursofOperationField = new JTextField(pharmacy.getHoursOfOperation());
            socialMediaLabel = new JLabel("Social Media Handle:");
            socialMediaField = new JTextField(pharmacy.getSocialMediaHandle());
            saveButton = new JButton("Save");
            saveButton.setForeground(Color.WHITE);
            saveButton.setFont(myFont);
            saveButton.setBackground(new Color(12, 35, 75));
            resetButton = new JButton("Reset");
            resetButton.setForeground(Color.WHITE);
            resetButton.setFont(myFont);
            resetButton.setBackground(new Color(12, 35, 75));


        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == saveButton) {
                    Pharmacy updatedPharmacy = getUpdatedPharmacy();
                    pharmacy.setName(updatedPharmacy.getName());
                    pharmacy.setWebsiteUrl(updatedPharmacy.getWebsiteUrl());
                    pharmacy.setOwner(updatedPharmacy.getOwner());
                    pharmacy.setPhoneNumber(updatedPharmacy.getPhoneNumber());
                    pharmacy.setHoursOfOperation(updatedPharmacy.getHoursOfOperation());
                    pharmacy.setSocialMediaHandle(updatedPharmacy.getSocialMediaHandle());
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == resetButton) {
                        pharmacyNameField.setText("");
                        websiteURLField.setText("");
                        ownerField.setText("");
                        phoneNumberField.setText("");
                        hoursofOperationField.setText("");
                        socialMediaField.setText("");
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
            add(saveButton);
            add(resetButton);
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