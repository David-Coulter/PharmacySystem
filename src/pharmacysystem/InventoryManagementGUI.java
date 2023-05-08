package pharmacysystem;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import pharmacysystem.Inventory.Prescription;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class InventoryManagementGUI extends JFrame implements ActionListener {
    
    private JButton addItemButton, removeItemButton, viewInventoryButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private ManagerViewInventoryPanel viewInventoryPanel;
    private AddItemInventoryPanel addItemInventoryPanel;
    private RemoveItemInventoryPanel removeItemPanel;
    private JPanel headerPanel;
    Font myFont = new Font("Proxima Nova", Font.BOLD, 14);
    
    public InventoryManagementGUI() {
        super("Inventory Management");
        ImageIcon img = new ImageIcon("C:\\Users\\Coulter\\Desktop\\PharmacySystem_Team1\\PharmacySystem\\src\\resources\\ua.png");
        this.setIconImage(img.getImage());

        // set up the header panel with an image
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/pharmacy.png"));
        JLabel header = new JLabel(icon);
        header.setPreferredSize(new Dimension(200, 50));
        headerPanel = new JPanel();
        headerPanel.add(header);
        headerPanel.setBackground(new Color(171, 5, 32));
        
        addItemButton = new JButton("<html>Add Item to<br> Inventory</html>");
        addItemButton.addActionListener(this);
        addItemButton.setFocusable(false);
        addItemButton.setForeground(Color.WHITE);
        addItemButton.setBackground(new Color(12, 35, 75));
        addItemButton.setFont(myFont);

        removeItemButton = new JButton("<html>Remove Item <br>from Inventory</html>");
        removeItemButton.addActionListener(this);
        removeItemButton.setFocusable(false);
        removeItemButton.setForeground(Color.WHITE);
        removeItemButton.setBackground(new Color(12, 35, 75));
        removeItemButton.setFont(myFont);
        
        viewInventoryButton = new JButton("View Inventory");
        viewInventoryButton.addActionListener(this);
        viewInventoryButton.setFocusable(false);
        viewInventoryButton.setForeground(Color.WHITE);
        viewInventoryButton.setBackground(new Color(12, 35, 75));
        viewInventoryButton.setFont(myFont);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBackground(new Color(171, 5, 32));
        buttonPanel.add(addItemButton);
        buttonPanel.add(removeItemButton);
        buttonPanel.add(viewInventoryButton);
        
        // Initialize the ManagerViewInventoryPanel object
        viewInventoryPanel = new ManagerViewInventoryPanel();
        addItemInventoryPanel = new AddItemInventoryPanel(viewInventoryPanel);
        removeItemPanel = new RemoveItemInventoryPanel(viewInventoryPanel);
        
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(addItemInventoryPanel, "AddItemPanel");
        mainPanel.add(removeItemPanel, "RemoveItemPanel");
        mainPanel.add(viewInventoryPanel, "ViewInventoryPanel");
        
        // add the header and buttons to the main menu
        add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("<html>Add Item to<br> Inventory</html>")) {
            cardLayout.show(mainPanel, "AddItemPanel");
        } else if (command.equals("<html>Remove Item <br>from Inventory</html>")) {
            cardLayout.show(mainPanel, "RemoveItemPanel");
        } else if (command.equals("View Inventory")) {
            cardLayout.show(mainPanel, "ViewInventoryPanel");
        }
    }

    public class AddItemInventoryPanel extends JPanel {
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
        private ManagerViewInventoryPanel viewInventoryPanel;
    
        Font myFont = new Font("Proxima Nova", Font.BOLD, 14);
        Font fieldFont = new Font("Proxima Nova", Font.PLAIN, 12);
    
        public AddItemInventoryPanel(ManagerViewInventoryPanel viewInventoryPanel) {
            this.viewInventoryPanel = viewInventoryPanel; // assign the parameter to the instance variable
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
            quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
            quantitySpinner.setFont(fieldFont);
    
            newinventoryLabel = new JLabel("New Inventory:");
            newinventoryLabel.setFont(fieldFont);
            newinventoryField = new JTextField();
            newinventoryField.setFont(fieldFont);
    
            saveButton = new JButton("Add Items");
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
                    int newInventory = currentQuantity + (int) quantitySpinner.getValue();
    
                    // Update the newinventoryField
                    newinventoryField.setText(Integer.toString(newInventory));
                }
            });
    
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String prescriptionName = (String) prescriptionNames.getSelectedItem();
                    Prescription prescription = inventory.getPrescriptions().get(prescriptionName);
                    int newInventory = Integer.parseInt(newinventoryField.getText());
                    inventoryField.setText(Integer.toString(newInventory));
                    prescription.setQuantity(newInventory);
                    viewInventoryPanel.updatePrescriptionQuantity(prescription, newInventory);
                    quantitySpinner.setValue(0);
                    newinventoryField.setText("");
                    JOptionPane.showMessageDialog(null, "Inventory Added.");
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
    }
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
        private ManagerViewInventoryPanel viewInventoryPanel;

        Font myFont = new Font("Proxima Nova", Font.BOLD, 14);
        Font fieldFont = new Font("Proxima Nova", Font.PLAIN, 12);
    
        public RemoveItemInventoryPanel(ManagerViewInventoryPanel viewInventoryPanel) {
            this.viewInventoryPanel = viewInventoryPanel;
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
            quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
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
                    String prescriptionName = (String) prescriptionNames.getSelectedItem();
                    Prescription prescription = inventory.getPrescriptions().get(prescriptionName);
                    int newInventory = Integer.parseInt(newinventoryField.getText());
                    inventoryField.setText(Integer.toString(newInventory));
                    prescription.setQuantity(newInventory);
                    viewInventoryPanel.updatePrescriptionQuantity(prescription, newInventory);
                    quantitySpinner.setValue(0);
                    newinventoryField.setText("");
                    JOptionPane.showMessageDialog(null, "Inventory Removed.");
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
    
    public class ManagerViewInventoryPanel extends JPanel {
        private JTable inventoryTable;
        private DefaultTableModel tableModel;
        private Inventory inventory;
    
        public ManagerViewInventoryPanel() {
            inventory = new Inventory();
            tableModel = new DefaultTableModel(new Object[]{"Prescription", "Expiration Date", "Cost", "Quantity"}, 0);
            inventoryTable = new JTable(tableModel);
    
            // Add the table to the panel
            JScrollPane scrollPane = new JScrollPane(inventoryTable);
            add(scrollPane);
    
            // Add all prescriptions to the table model
            for (Map.Entry<String, Prescription> entry : inventory.getPrescriptions().entrySet()) {
                String name = entry.getKey();
                Prescription prescription = entry.getValue();
                LocalDate expirationDate = prescription.getExpirationDate();
                double cost = prescription.getCost();
                int quantity = prescription.getQuantity();
                Object[] row = {name, expirationDate, cost, quantity};
                tableModel.addRow(row);
            }
        }
    
        public void updatePrescriptionQuantity(Prescription prescription, int newQuantity) {
            // Update the quantity of the Prescription object
            prescription.setQuantity(newQuantity);
            // Loop through the table to find the prescription with the matching name
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String name = (String) tableModel.getValueAt(i, 0);
                if (name.equals(prescription.getName())) {
                    // Update the quantity of the prescription
                    tableModel.setValueAt(newQuantity, i, 3);
                    break;
                }
            }
        }
    }
    
    
    
    public static void main(String[] args) {
        InventoryManagementGUI gui = new InventoryManagementGUI();
    }
}
