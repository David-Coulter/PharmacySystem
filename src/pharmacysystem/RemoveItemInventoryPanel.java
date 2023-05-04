package pharmacysystem;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class RemoveItemInventoryPanel extends JPanel {
	private JComboBox<String> prescriptionNames;
    private JLabel prescriptionLabel;
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
    private Inventory inventory;
    Font myFont = new Font("Proxima Nova", Font.BOLD, 14);
    Font fieldFont = new Font("Proxima Nova", Font.PLAIN, 12);

    public RemoveItemInventoryPanel() {
    	inventory = new Inventory();

        // Initialize the UI components
        prescriptionNames = new JComboBox<>(inventory.getPrescriptions().keySet().toArray(new String[0]));
        prescriptionNames.setFont(fieldFont);
        prescriptionLabel = new JLabel("Prescription: ");
        prescriptionLabel.setFont(fieldFont);

        expirationDateLabel = new JLabel("Expiration Date:");
        expirationDateLabel.setFont(fieldFont);

        expirationDateField = new JTextField();
        expirationDateField.setFont(fieldFont);

        costLabel = new JLabel("Cost:");
        costLabel.setFont(fieldFont);
        costField = new JTextField();
        costField.setFont(fieldFont);

        inventoryLabel = new JLabel("Inventory:");
        inventoryLabel.setFont(fieldFont);
        inventoryField = new JTextField();
        inventoryField.setFont(fieldFont);

        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(fieldFont);
        quantitySpinner = new JSpinner();
        quantitySpinner.setFont(fieldFont);

        newinventoryLabel = new JLabel("New Inventory:");
        newinventoryLabel.setFont(fieldFont);
        newinventoryField = new JTextField();
        newinventoryField.setFont(fieldFont);

        saveButton = new JButton("Remove Items");
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(myFont);
        saveButton.setBackground(new Color(12, 35, 75));

        resetButton = new JButton("Reset");
        resetButton.setForeground(Color.WHITE);
        resetButton.setFont(myFont);
        resetButton.setBackground(new Color(12, 35, 75));

        // Add the UI components to the frame
        setLayout(new GridLayout(7, 2));
        add(prescriptionLabel);
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

                // Update the inventory table
                updateInventoryTable();
            }
        });
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == resetButton) {
                        expirationDateField.setText("");
                        inventoryField.setText("");
                        costField.setText("");
                        quantitySpinner.setValue(0);
                        newinventoryField.setText("");
                }
            }
        });

    }

    public void updateInventoryTable() {
        // Get the table model
        DefaultTableModel tableModel = (DefaultTableModel) inventory.getInventoryTable().getModel();

        // Remove all rows from the table
        tableModel.setRowCount(0);

        // Loop through the prescriptions in the inventory and add them to the table
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (Map.Entry<String, Inventory.Prescription> entry : inventory.getPrescriptions().entrySet()) {
            String name = entry.getKey();
            Inventory.Prescription prescription = entry.getValue();

            // Add the prescription information to a new row in the table
            Object[] rowData = {
                    name,
                    prescription.getExpirationDate().format(formatter),
                    prescription.getCost(),
                    prescription.getQuantity()
            };
            tableModel.addRow(rowData);
        }
    }

}
