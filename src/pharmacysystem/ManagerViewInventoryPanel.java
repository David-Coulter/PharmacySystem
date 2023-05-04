package pharmacysystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ManagerViewInventoryPanel extends JPanel {
    private JLabel titleLabel;
    private JTable inventoryTable;
    private DefaultTableModel tableModel;
    private Inventory inventory;
    Font myFont = new Font("Proxima Nova", Font.BOLD, 12);

    public ManagerViewInventoryPanel() {
        setLayout(new BorderLayout());
        titleLabel = new JLabel("Current Inventory", JLabel.CENTER);
        titleLabel.setFont(myFont);
        String[] columnNames = {"Item Name", "Expiration Date","Item Price", "Item Quantity"};
        tableModel = new DefaultTableModel(columnNames, 0);
        inventoryTable = new JTable(tableModel);
        inventoryTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(inventoryTable);
        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        inventory = new Inventory(); // initialize the inventory instance variable
        updateInventoryTable(); // update the table with the inventory data
    }

    public void updateInventoryTable() {
        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        for (Map.Entry<String, Inventory.Prescription> entry : inventory.getPrescriptions().entrySet()) {
            String name = entry.getKey();
            Inventory.Prescription prescription = entry.getValue();
            Object[] rowData = {name, prescription.getExpirationDate().format(formatter), prescription.getCost(), prescription.getQuantity()};
            tableModel.addRow(rowData);
        }
    }

}
