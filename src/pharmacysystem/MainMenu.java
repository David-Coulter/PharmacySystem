package pharmacysystem;

import javax.swing.*;
import java.awt.*;


    public class MainMenu extends JFrame {
        private JButton patientBtn;
        private JButton inventoryBtn;
        private JButton transactionBtn;
        private JButton reportBtn;
        private JButton pharmacyBtn;
        private JPanel headerPanel;

        Font myFont = new Font("Proxima Nova", Font.PLAIN, 20);

        public MainMenu() {
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

            // add the buttons to the main menu
            patientBtn = new JButton("Patient Information");
            patientBtn.setFocusable(false);
            patientBtn.setForeground(Color.WHITE);
            patientBtn.setBackground(new Color(12, 35, 75));
            patientBtn.setFont(myFont); // set the font for each button

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

            reportBtn = new JButton("Report Generation");
            reportBtn.setFocusable(false);
            reportBtn.setForeground(Color.WHITE);
            reportBtn.setBackground(new Color(12, 35, 75));
            reportBtn.setFont(myFont);
        

            pharmacyBtn = new JButton("Pharmacy Information");
            pharmacyBtn.setFocusable(false);
            pharmacyBtn.setForeground(Color.WHITE);
            pharmacyBtn.setBackground(new Color(12, 35, 75));
            pharmacyBtn.setFont(myFont);

            // add event listeners to the buttons
            patientBtn.addActionListener(e -> openPatientGUI());
            inventoryBtn.addActionListener(e -> openInventoryGUI());
            transactionBtn.addActionListener(e -> openTransactionGUI());
            reportBtn.addActionListener(e -> openReportGUI());
            pharmacyBtn.addActionListener(e -> openPharmacyGUI());

       
         // arrange the buttons in a grid layout
            JPanel buttonpanel = new JPanel(new GridLayout(3, 3));
            buttonpanel.setBackground(Color.WHITE);
            buttonpanel.add(patientBtn);
            buttonpanel.add(inventoryBtn);
            buttonpanel.add(transactionBtn);
            buttonpanel.add(reportBtn);
            buttonpanel.add(pharmacyBtn);

            // add the header and buttons to the main menu
            add(headerPanel, BorderLayout.NORTH);
            add(buttonpanel, BorderLayout.CENTER);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(600, 600);
            setVisible(true);
            setLocationRelativeTo(null);

        }

    private void openPatientGUI() {
        PatientManagementGUI patientGUI = new PatientManagementGUI();
        patientGUI.setVisible(true);
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

    private void openReportGUI() {
        // code to open the report GUI
        ReportGUI reportGUI = new ReportGUI();
        reportGUI.setVisible(true);
    }

    private void openPharmacyGUI() {
        // code to open the pharmacy GUI
        PharmacyGUI pharmacyGUI = new PharmacyGUI();
        pharmacyGUI.setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
