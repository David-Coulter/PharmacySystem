package pharmacysystem;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

public class TransactionManagementGUI extends JFrame implements ActionListener {

    private JButton fillPrescriptionButton, processTransactionButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private FillPrescriptionPanel fillPrescriptionPanel;
    private ProcessTransactionPanel processTransactionPanel;
    private JPanel headerPanel;
    Font myFont = new Font("Proxima Nova", Font.BOLD, 14);

    public TransactionManagementGUI() {
        super("Transaction Management");
        ImageIcon img = new ImageIcon("C:\\Users\\Coulter\\Desktop\\PharmacySystem_Team1\\PharmacySystem\\src\\resources\\ua.png");
            this.setIconImage(img.getImage());

        // set up the header panel with an image
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/pharmacy.png"));
        JLabel header = new JLabel(icon);
        header.setPreferredSize(new Dimension(200, 50));
        headerPanel = new JPanel();
        headerPanel.add(header);
        headerPanel.setBackground(new Color(171, 5, 32));

        fillPrescriptionButton = new JButton("Fill Prescription");
        fillPrescriptionButton.addActionListener(this);
        fillPrescriptionButton.setFont(myFont);
        fillPrescriptionButton.setFocusable(false);
        fillPrescriptionButton.setForeground(Color.WHITE);
        fillPrescriptionButton.setBackground(new Color(12, 35, 75));

        processTransactionButton = new JButton("Process Transaction");
        processTransactionButton.setFocusable(false);
        processTransactionButton.setFont(myFont);
        processTransactionButton.addActionListener(this);
        processTransactionButton.setForeground(Color.WHITE);
        processTransactionButton.setBackground(new Color(12, 35, 75));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setPreferredSize(new Dimension(500, 30));
        buttonPanel.add(fillPrescriptionButton);
        buttonPanel.add(processTransactionButton);

        fillPrescriptionPanel = new FillPrescriptionPanel();
        processTransactionPanel = new ProcessTransactionPanel();

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(fillPrescriptionPanel, "FillPrescriptionPanel");
        mainPanel.add(processTransactionPanel, "ProcessTransactionPanel");
        mainPanel.setPreferredSize(new Dimension(600, 420));

        add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fillPrescriptionButton) {
            cardLayout.show(mainPanel, "FillPrescriptionPanel");
        } else if (e.getSource() == processTransactionButton) {
            cardLayout.show(mainPanel, "ProcessTransactionPanel");
        }
    }

    public static void main(String[] args) {
        TransactionManagementGUI gui = new TransactionManagementGUI();
    }

    private class FillPrescriptionPanel extends JPanel {
    	private JComboBox<String> prescriptionNames;
        private JLabel expirationDateLabel;
        private JTextField expirationDateField;
        private JLabel costLabel;
        private JTextField costField;
        private JLabel inventoryLabel;
        private JTextField inventoryField;
        private JLabel quantityLabel;
        private JSpinner quantitySpinner;
        private JLabel newinventoryLabel;
        private JTextField newinventoryField;
        private JButton saveButton;
        private JButton resetButton;
        public Inventory inventory;
    
        
        public FillPrescriptionPanel() {
        	inventory = new Inventory();

        // Initialize the UI components
        prescriptionNames = new JComboBox<>(inventory.getPrescriptions().keySet().toArray(new String[0]));
        expirationDateLabel = new JLabel("Expiration Date:");
        expirationDateField = new JTextField();
        costLabel = new JLabel("Cost:");
        costField = new JTextField();
        inventoryLabel = new JLabel("Current Inventory:");
        inventoryField = new JTextField();
        newinventoryLabel = new JLabel("New Inventory:");
        newinventoryField = new JTextField();
        quantityLabel = new JLabel("Quantity Needed:");
        quantitySpinner = new JSpinner();
        saveButton = new JButton("Save");
        saveButton.setFont(myFont);
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(12, 35, 75));
        resetButton = new JButton("Reset");
        resetButton.setFont(myFont);
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(12, 35, 75));

        // Add the UI components to the frame
        setLayout(new GridLayout(7, 2));
        add(new JLabel("Prescription Name:"));
        add(prescriptionNames);
        add(expirationDateLabel);
        add(expirationDateField);
        add(costLabel);
        add(costField);
        add(inventoryLabel);
        add(inventoryField);
        add(quantityLabel);
        add(quantitySpinner);
        add(newinventoryLabel);
        add(newinventoryField);
        add(saveButton);
        add(resetButton);

        // Add a listener to the prescription name drop-down menu
        prescriptionNames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected prescription name
                String prescriptionName = (String) prescriptionNames.getSelectedItem();

                // Get the prescription object from the inventory
                Inventory.Prescription prescription = inventory.getPrescription(prescriptionName);

                // Populate the expiration date, cost, and inventory fields
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                expirationDateField.setText(prescription.getExpirationDate().format(formatter));
                costField.setText(Double.toString(prescription.getCost()));
                inventoryField.setText(Integer.toString(prescription.getQuantity()));

                // Set the quantity spinner value to 1
                quantitySpinner.setValue(1);
            }
        });
        // Add a listener to the quantity spinner
        quantitySpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Get the current quantity and inventory
                int currentQuantity = Integer.parseInt(inventoryField.getText());

                // Calculate the new inventory
                int newInventory = currentQuantity - (int) quantitySpinner.getValue();

                // Update the newinventoryField
                newinventoryField.setText(Integer.toString(newInventory));
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected prescription name and quantity
                String prescriptionName = (String) prescriptionNames.getSelectedItem();
                int quantity = (int) quantitySpinner.getValue();

                // Update the prescription quantity in the inventory
                Inventory.Prescription prescription = inventory.getPrescription(prescriptionName);
                prescription.setQuantity(quantity);

                // Update the quantity spinner
                SpinnerNumberModel model = (SpinnerNumberModel) quantitySpinner.getModel();
                model.setValue(quantity);

                JOptionPane.showMessageDialog(null, "Prescription Filled.");

            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == resetButton) {
                        expirationDateField.setText("");
                        costField.setText("");
                        quantitySpinner.setValue(0);
                        newinventoryField.setText("");
                }
            }
        });

    }
    }
  
    private class ProcessTransactionPanel extends JPanel {
    	private JComboBox<String> prescriptionNames;
        private JLabel expirationDateLabel;
        private JTextField expirationDateField;
        private JLabel costLabel;
        private JTextField costField;
        private JLabel quantityLabel;
        private JSpinner quantitySpinner;
        private JLabel totalCostLabel;
        private JTextField totalCostField;
        private JButton saveButton;
        private JButton resetButton;
        private Inventory inventory;
        
        public ProcessTransactionPanel() {
        	inventory = new Inventory();

        // Initialize the UI components
        prescriptionNames = new JComboBox<>(inventory.getPrescriptions().keySet().toArray(new String[0]));
        expirationDateLabel = new JLabel("Expiration Date:");
        expirationDateField = new JTextField();
        costLabel = new JLabel("Cost Per:");
        costField = new JTextField();
        quantityLabel = new JLabel("Quantity Needed:");
        quantitySpinner = new JSpinner();
        totalCostLabel = new JLabel("Total Cost:");
        totalCostField = new JTextField();
        saveButton = new JButton("Process Transaction");
        saveButton.setFont(myFont);
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(12, 35, 75));
        resetButton = new JButton("Reset");
        resetButton.setFont(myFont);
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(new Color(12, 35, 75));

        // Add the UI components to the frame
        setLayout(new GridLayout(6, 2));
        add(new JLabel("Prescription Name:"));
        add(prescriptionNames);
        add(expirationDateLabel);
        add(expirationDateField);
        add(costLabel);
        add(costField);
        add(quantityLabel);
        add(quantitySpinner);
        add(totalCostLabel);
        add(totalCostField);
        add(saveButton);
        add(resetButton);

        prescriptionNames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected prescription name
                String prescriptionName = (String) prescriptionNames.getSelectedItem();
        
                // Get the prescription object from the inventory
                Inventory.Prescription prescription = inventory.getPrescription(prescriptionName);
        
                // Populate the expiration date, cost, and inventory fields
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                expirationDateField.setText(prescription.getExpirationDate().format(formatter));
                costField.setText(Double.toString(prescription.getCost()));
        
                // Set the quantity spinner value to 0
                quantitySpinner.setValue(0);
        
                // Update the total cost field
                double totalCost = prescription.getCost() * (int) quantitySpinner.getValue();
                totalCostField.setText(Double.toString(totalCost));
            }
        });

        quantitySpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Get the current cost and quantity
                double cost = Double.parseDouble(costField.getText());
                int quantity = (int) quantitySpinner.getValue();
                
                // Calculate the total cost
                double totalCost = cost * quantity;
                
                // Update the total cost field
                totalCostField.setText(String.format("%.2f", totalCost));
            }
        });
        
        

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected prescription name and quantity
                String prescriptionName = (String) prescriptionNames.getSelectedItem();
                int quantity = (int) quantitySpinner.getValue();

                // Update the prescription quantity in the inventory
                Inventory.Prescription prescription = inventory.getPrescription(prescriptionName);
                prescription.setQuantity(quantity);

                // Update the quantity spinner
                SpinnerNumberModel model = (SpinnerNumberModel) quantitySpinner.getModel();
                model.setValue(quantity);

                 // Display payment process message with total cost
                String message = "Process Payment for " + totalCostField.getText();
                JOptionPane.showMessageDialog(null, message);
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == resetButton) {
                        expirationDateField.setText("");
                        costField.setText("");
                        quantitySpinner.setValue(0);
                        totalCostField.setText("");
                }
            }
        });

    }
    }
    }
