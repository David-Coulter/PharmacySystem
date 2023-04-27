package pharmacysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

public class TransactionManagementGUI extends JFrame implements ActionListener {

    private JButton fillPrescriptionButton, processTransactionButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private FillPrescriptionPanel fillPrescriptionPanel;
    private ProcessTransactionPanel processTransactionPanel;

    public TransactionManagementGUI() {
        super("Transaction Management");

        fillPrescriptionButton = new JButton("Fill Prescription");
        fillPrescriptionButton.addActionListener(this);

        processTransactionButton = new JButton("Process Transaction");
        processTransactionButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(fillPrescriptionButton);
        buttonPanel.add(processTransactionButton);

        fillPrescriptionPanel = new FillPrescriptionPanel();
        processTransactionPanel = new ProcessTransactionPanel();

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(fillPrescriptionPanel, "FillPrescriptionPanel");
        mainPanel.add(processTransactionPanel, "ProcessTransactionPanel");

        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
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
        private JButton saveButton;
        private Inventory inventory;
        
        public FillPrescriptionPanel() {
        	inventory = new Inventory();

        // Initialize the UI components
        prescriptionNames = new JComboBox<>(inventory.getPrescriptions().keySet().toArray(new String[0]));
        expirationDateLabel = new JLabel("Expiration Date:");
        expirationDateField = new JTextField();
        costLabel = new JLabel("Cost:");
        costField = new JTextField();
        inventoryLabel = new JLabel("Inventory:");
        inventoryField = new JTextField();
        quantityLabel = new JLabel("Quantity:");
        quantitySpinner = new JSpinner();
        saveButton = new JButton("Save");

        // Add the UI components to the frame
        setLayout(new GridLayout(6, 2));
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
        add(new JLabel());
        add(saveButton);

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
        private JLabel inventoryLabel;
        private JTextField inventoryField;
        private JLabel quantityLabel;
        private JSpinner quantitySpinner;
        private JButton saveButton;
        private Inventory inventory;
        
        public ProcessTransactionPanel() {
        	inventory = new Inventory();

        // Initialize the UI components
        prescriptionNames = new JComboBox<>(inventory.getPrescriptions().keySet().toArray(new String[0]));
        expirationDateLabel = new JLabel("Expiration Date:");
        expirationDateField = new JTextField();
        costLabel = new JLabel("Cost:");
        costField = new JTextField();
        quantityLabel = new JLabel("Quantity:");
        quantitySpinner = new JSpinner();
        saveButton = new JButton("Save");

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
        add(new JLabel());
        add(saveButton);

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

                // Set the quantity spinner value to 1
                quantitySpinner.setValue(1);
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

            }
        });

    }
    }
    }
