package pharmacysystem;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AddItemInventoryPanel extends JPanel {
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

    public AddItemInventoryPanel() {
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

                // Update the inventory table
                updateInventoryTable();
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