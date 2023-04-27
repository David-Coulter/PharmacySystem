package pharmacysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventoryManagementGUI extends JFrame implements ActionListener {
    
    private JButton addItemButton, viewInventoryButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private AddItemInventoryPanel addItemPanel;
    private ManagerViewInventoryPanel viewInventoryPanel;
    
    public InventoryManagementGUI() {
        super("Inventory Management");
        
        addItemButton = new JButton("Add Item to Inventory");
        addItemButton.addActionListener(this);
        
        viewInventoryButton = new JButton("View Inventory");
        viewInventoryButton.addActionListener(this);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(addItemButton);
        buttonPanel.add(viewInventoryButton);
        
        addItemPanel = new AddItemInventoryPanel();
        viewInventoryPanel = new ManagerViewInventoryPanel();
        
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(addItemPanel, "AddItemPanel");
        mainPanel.add(viewInventoryPanel, "ViewInventoryPanel");
        
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addItemButton) {
            cardLayout.show(mainPanel, "AddItemPanel");
        } else if (e.getSource() == viewInventoryButton) {
            cardLayout.show(mainPanel, "ViewInventoryPanel");
        } 
    }
    
    public static void main(String[] args) {
        InventoryManagementGUI gui = new InventoryManagementGUI();
    }
}
