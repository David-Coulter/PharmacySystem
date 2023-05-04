package pharmacysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventoryManagementGUI extends JFrame implements ActionListener {
    
    private JButton addItemButton, removeItemButton, viewInventoryButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private AddItemInventoryPanel addItemPanel;
    private RemoveItemInventoryPanel removeItemPanel;
    private ManagerViewInventoryPanel viewInventoryPanel;
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
        
        addItemPanel = new AddItemInventoryPanel();
        removeItemPanel = new RemoveItemInventoryPanel();
        viewInventoryPanel = new ManagerViewInventoryPanel();
        
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(addItemPanel, "AddItemPanel");
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
        if (e.getSource() == addItemButton) {
            cardLayout.show(mainPanel, "AddItemPanel");
        } else if(e.getSource()==removeItemButton){
            cardLayout.show(mainPanel, "RemoveItemPanel");
        }
        else if (e.getSource() == viewInventoryButton) {
            cardLayout.show(mainPanel, "ViewInventoryPanel");
        } 
    }
    
    public static void main(String[] args) {
        InventoryManagementGUI gui = new InventoryManagementGUI();
    }
}
