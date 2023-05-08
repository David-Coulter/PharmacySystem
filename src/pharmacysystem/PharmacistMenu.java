package pharmacysystem;

import javax.swing.*;
import java.awt.*;


    public class PharmacistMenu extends JFrame {
        private JButton inventoryBtn;
        private JButton transactionBtn;
        private JPanel headerPanel;

        Font myFont = new Font("Proxima Nova", Font.PLAIN, 20);

        public PharmacistMenu() {
            // set up the main menu GUI
            ImageIcon img = new ImageIcon("C:\\Users\\Coulter\\Desktop\\PharmacySystem_Team1\\PharmacySystem\\src\\resources\\ua.png");
            this.setIconImage(img.getImage());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Pharmacy Management System");

            // set up the header panel with an image
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/pharmacy.png"));
            JLabel header = new JLabel(icon);
            header.setPreferredSize(new Dimension(200, 50));
            headerPanel = new JPanel();
            headerPanel.add(header);
            headerPanel.setBackground(new Color(171, 5, 32));

            inventoryBtn = new JButton("Inventory Management");
            inventoryBtn.setFocusable(false);
            inventoryBtn.setForeground(Color.WHITE);
            inventoryBtn.setBackground(new Color(12, 35, 75));
            inventoryBtn.setFont(myFont);

            transactionBtn = new JButton("Transaction Management");
            transactionBtn.setFocusable(false);
            transactionBtn.setForeground(Color.WHITE);
            transactionBtn.setBackground(new Color(12, 35, 75));
            transactionBtn.setFont(myFont);


            // add event listeners to the buttons
            inventoryBtn.addActionListener(e -> openInventoryGUI());
            transactionBtn.addActionListener(e -> openTransactionGUI());
       
         // arrange the buttons in a grid layout
            JPanel buttonpanel = new JPanel(new GridLayout(2, 2));
            buttonpanel.setBackground(Color.WHITE);
            buttonpanel.add(inventoryBtn);
            buttonpanel.add(transactionBtn);

            // add the header and buttons to the main menu
            add(headerPanel, BorderLayout.NORTH);
            add(buttonpanel, BorderLayout.CENTER);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setSize(600, 600);
            setVisible(true);
            setLocationRelativeTo(null);

        }

    private void openInventoryGUI() {
        InventoryManagementGUI inventoryGUI = new InventoryManagementGUI();
        inventoryGUI.setVisible(true);
    }

    private void openTransactionGUI() {
        // code to open the transaction GUI
        TransactionManagementGUI transactionGUI = new TransactionManagementGUI();
        transactionGUI.setVisible(true);
    } 

    public static void main(String[] args) {
        new PharmacistMenu();
    }
}
