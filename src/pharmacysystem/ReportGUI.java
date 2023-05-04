package pharmacysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGUI extends JFrame implements ActionListener {

    private JButton inventoryReportButton, patientReportButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private InventoryReportPanel inventoryReportPanel;
    private PatientReportPanel patientReportPanel;
    private JPanel headerPanel;
    Font myFont = new Font("Proxima Nova", Font.BOLD, 14);

    public ReportGUI() {
        super("Report Generation");
        ImageIcon img = new ImageIcon("C:\\Users\\Coulter\\Desktop\\PharmacySystem_Team1\\PharmacySystem\\src\\resources\\ua.png");
            this.setIconImage(img.getImage());
        // set up the header panel with an image
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/pharmacy.png"));
        JLabel header = new JLabel(icon);
        header.setPreferredSize(new Dimension(200, 50));
        headerPanel = new JPanel();
        headerPanel.add(header);
        headerPanel.setBackground(new Color(171, 5, 32));

        inventoryReportButton = new JButton("View Inventory");
        inventoryReportButton.addActionListener(this);
        inventoryReportButton.setFont(myFont);
        inventoryReportButton.setFocusable(false);
        inventoryReportButton.setForeground(Color.WHITE);
        inventoryReportButton.setBackground(new Color(12, 35, 75));

        patientReportButton = new JButton("View Patients");
        patientReportButton.setFocusable(false);
        patientReportButton.setFont(myFont);
        patientReportButton.addActionListener(this);
        patientReportButton.setForeground(Color.WHITE);
        patientReportButton.setBackground(new Color(12, 35, 75));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.setPreferredSize(new Dimension(500, 30));
        buttonPanel.add(inventoryReportButton);
        buttonPanel.add(patientReportButton);


        inventoryReportPanel = new InventoryReportPanel();
        patientReportPanel = new PatientReportPanel();

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(inventoryReportPanel, "inventoryReportPanel");
        mainPanel.add(patientReportPanel, "patientReportPanel");
        mainPanel.setPreferredSize(new Dimension(600, 400));

        add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 600);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inventoryReportButton) {
            InventoryReportPanel inventoryReportPanel = (InventoryReportPanel) mainPanel.getComponent(0);
            inventoryReportPanel.initialize();
            cardLayout.show(mainPanel, "inventoryReportPanel");
        } else if (e.getSource() == patientReportButton) {
            PatientReportPanel patientReportPanel = (PatientReportPanel) mainPanel.getComponent(1);
            patientReportPanel.initialize();
            cardLayout.show(mainPanel, "patientReportPanel");
        }
    }
    
    public static void main(String[] args) {
        ReportGUI gui = new ReportGUI();
    }

    public class InventoryReportPanel extends JPanel {
        private JButton reportButton;
        private Inventory inventory;
        private JTextArea inventoryTextArea;
    
        public InventoryReportPanel() {
            inventory = new Inventory();
    
            // Initialize the UI components
            reportButton = new JButton("Generate Report");
            reportButton.setFont(myFont);
            reportButton.setPreferredSize(new Dimension(600, 50));
            reportButton.setForeground(Color.WHITE);
            reportButton.setBackground(new Color(12, 35, 75));

            inventoryTextArea = new JTextArea();
    
            // Add the UI components to the frame
            setLayout(new BorderLayout());
            add(reportButton, BorderLayout.SOUTH);
            add(new JScrollPane(inventoryTextArea), BorderLayout.CENTER);
    
            reportButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        FileWriter writer = new FileWriter("Inventory Report.csv");
    
                        // Write the headers row
                        writer.append("Prescription Name");
                        writer.append(",");
                        writer.append("Expiration Date");
                        writer.append(",");
                        writer.append("Cost");
                        writer.append(",");
                        writer.append("Inventory");
                        writer.append("\n");
    
                        // Write the data rows
                        for (String prescriptionName : inventory.getPrescriptions().keySet()) {
                            Inventory.Prescription prescription = inventory.getPrescription(prescriptionName);
                            writer.append(prescriptionName);
                            writer.append(",");
                            writer.append(prescription.getExpirationDate().toString());
                            writer.append(",");
                            writer.append(String.valueOf(prescription.getCost()));
                            writer.append(",");
                            writer.append(String.valueOf(prescription.getQuantity()));
                            writer.append("\n");
                        }
    
                        writer.flush();
                        writer.close();
    
                        JOptionPane.showMessageDialog(null, "Inventory report saved to file.");
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "Error saving inventory report to file.");
                    }
                }
            });
        }
    
        public void initialize() {
            // Clear the text area
            inventoryTextArea.setText("");
    
            // Add the inventory data
            for (String prescriptionName : inventory.getPrescriptions().keySet()) {
                Inventory.Prescription prescription = inventory.getPrescription(prescriptionName);
                String row = prescriptionName + "\t" + prescription.getExpirationDate().toString() + "\t" +
                        prescription.getCost() + "\t" + prescription.getQuantity() + "\n";
                inventoryTextArea.append(row);
            }
        }
    }
    public class PatientReportPanel extends JPanel {
        private JButton reportButton;
        private Patient patient;
        private JTextArea patientTextArea;

        public PatientReportPanel() {
        List<Patient> patients = Patient.getPatients();
        //patient = patients.get(0); // get the first patient in the list
    
        // Initialize the UI components
        reportButton = new JButton("Generate Report");
        reportButton.setForeground(Color.WHITE);
        reportButton.setBackground(new Color(12, 35, 75));

        patientTextArea = new JTextArea();
    
        // Add the UI components to the frame
        setLayout(new BorderLayout());
        add(reportButton, BorderLayout.SOUTH);
        add(new JScrollPane(patientTextArea), BorderLayout.CENTER);
    
        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    FileWriter writer = new FileWriter("Patient Report.csv");
    
                    // Write the headers row
                    writer.append("Patient Name:");
                    writer.append(" ");
                    writer.append("DOB:");
                    writer.append(" ");
                    writer.append("Address:");
                    writer.append(" ");
                    writer.append("Insurance Provider:");
                    writer.append(" ");
                    writer.append("Policy Number:");
                    writer.append("\n");
                    
                    for (Patient patient : patients) {
                    // Write the data row for the patient
                    writer.append(patient.getName());
                    writer.append(",");
                    writer.append(patient.getDateOfBirth().toString());
                    writer.append(",");
                    writer.append(patient.getAddress());
                    writer.append(",");
                    writer.append(patient.getInsuranceProvider());
                    writer.append(",");
                    writer.append(patient.getPolicyNumber());
                    writer.append("\n");
                    }
    
                    writer.flush();
                    writer.close();
    
                    JOptionPane.showMessageDialog(null, "Patient report saved to file.");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error saving patient report to file.");
                }
            }
        });
    }

    public void initialize() {
        // Clear the text area
        patientTextArea.setText("");
    
        // Get the list of patients
        List<Patient> patients = Patient.getPatients();
    
        // Loop through the patients and append their information to the text area
        for (Patient patient : patients) {
            String patientInfo = "Name: " + patient.getName() + "\n" +
                                 "Date of Birth: " + patient.getDateOfBirth().toString() + "\n" +
                                 "Address: " + patient.getAddress() + "\n" +
                                 "Phone Number: " + patient.getPhoneNumber() + "\n" +
                                 "Insurance Provider: " + patient.getInsuranceProvider() + "\n" +
                                 "Policy Number: " + patient.getPolicyNumber() + "\n\n";
            patientTextArea.append(patientInfo);
        }
    }    
    }
}