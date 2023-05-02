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

        public MainMenu() {
            // set up the main menu GUI
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Pharmacy Management System");

            // set up the header panel with an image
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/pharmacy.png"));
            JLabel header = new JLabel(icon);
            header.setPreferredSize(new Dimension(50, 50));
            headerPanel = new JPanel();
            headerPanel.add(header);

            // add the buttons to the main menu
            patientBtn = new JButton("Patient Information");
            inventoryBtn = new JButton("Inventory Management");
            transactionBtn = new JButton("Transaction Management");
            reportBtn = new JButton("Report Generation");
            pharmacyBtn = new JButton("Pharmacy Information");

            // add event listeners to the buttons
            patientBtn.addActionListener(e -> openPatientGUI());
            inventoryBtn.addActionListener(e -> openInventoryGUI());
            transactionBtn.addActionListener(e -> openTransactionGUI());
            reportBtn.addActionListener(e -> openReportGUI());
            pharmacyBtn.addActionListener(e -> openPharmacyGUI());

       
         // arrange the buttons in a grid layout
            JPanel buttonpanel = new JPanel(new GridLayout(3, 3));
            buttonpanel.add(patientBtn);
            buttonpanel.add(inventoryBtn);
            buttonpanel.add(transactionBtn);
            buttonpanel.add(reportBtn);
            buttonpanel.add(pharmacyBtn);

            // add the header and buttons to the main menu
            add(headerPanel, BorderLayout.NORTH);
            add(buttonpanel, BorderLayout.CENTER);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setVisible(true);
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
