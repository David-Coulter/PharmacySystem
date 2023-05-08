package pharmacysystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class PatientManagementGUI extends JFrame implements ActionListener {
    private JButton createPatientButton, viewPatientButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private CreatePatientPanel createPatientPanel;
    private ViewPatientPanel viewPatientPanel;
    private HashMap<String, Patient> patientMap;
    private JPanel headerPanel;
    Font myFont = new Font("Proxima Nova", Font.BOLD, 14);

    public PatientManagementGUI() {
        super("Patient Management");
        ImageIcon img = new ImageIcon("C:\\Users\\Coulter\\Desktop\\PharmacySystem_Team1\\PharmacySystem\\src\\resources\\ua.png");
            this.setIconImage(img.getImage());
        // set up the header panel with an image
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/pharmacy.png"));
        JLabel header = new JLabel(icon);
        header.setPreferredSize(new Dimension(200, 50));
        headerPanel = new JPanel();
        headerPanel.add(header);
        headerPanel.setBackground(new Color(171, 5, 32));
        
        createPatientButton = new JButton("Create Patient");
        createPatientButton.addActionListener(this);
        createPatientButton.setFont(myFont);
        createPatientButton.setFocusable(false);
        createPatientButton.setForeground(Color.WHITE);
        createPatientButton.setBackground(new Color(12, 35, 75));
        
        viewPatientButton = new JButton("View Patient");
        viewPatientButton.setFocusable(false);
        viewPatientButton.setFont(myFont);
        viewPatientButton.addActionListener(this);
        viewPatientButton.setForeground(Color.WHITE);
        viewPatientButton.setBackground(new Color(12, 35, 75));
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBackground(new Color(171, 5, 32));
        buttonPanel.setPreferredSize(new Dimension(500, 30));
        buttonPanel.add(createPatientButton);
        buttonPanel.add(viewPatientButton);

        createPatientPanel = new CreatePatientPanel(this);
        viewPatientPanel = new ViewPatientPanel();
        
        // Set up panels
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        mainPanel.add(createPatientPanel, "createPatient");
        mainPanel.add(viewPatientPanel, "viewPatient");
        mainPanel.setPreferredSize(new Dimension(600, 420));
        
        // add the header and buttons to the main menu
        add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        getContentPane().add(mainPanel, BorderLayout.SOUTH);

        // Set up frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,600);
        setVisible(true);
        setLocationRelativeTo(null);

        
        // Set up patient data
        patientMap = new HashMap<>();
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Create Patient Account")) {
            createPatientPanel.submit();
        } else if (command.equals("Back to Main Menu")) {
            cardLayout.show(mainPanel, "mainMenu");
        } else if (command.equals("View Patient")) {
            cardLayout.show(mainPanel, "viewPatient");
        } else if (command.equals("Create Patient")) {
            cardLayout.show(mainPanel, "createPatient");
        }
    }

    private class CreatePatientPanel extends JPanel {
        private JTextField nameTextField;
        private DatePicker dobDatePicker;
        private JTextField addressTextField;
        private JTextField phoneTextField;
        private JTextField insuranceProviderTextField;
        private JTextField policyNumberTextField;

        public CreatePatientPanel(ActionListener listener) {
            setLayout(new GridLayout(7, 2));
            add(new JLabel("Name:"));
            nameTextField = new JTextField();
            add(nameTextField);
            add(new JLabel("Date of Birth:"));
            dobDatePicker = new DatePicker();
            DatePickerSettings settings = new DatePickerSettings();
            settings.setAllowEmptyDates(false);
            dobDatePicker.setSettings(settings);
            add(dobDatePicker);
            add(new JLabel("Address:"));
            addressTextField = new JTextField();
            add(addressTextField);
            add(new JLabel("Phone Number:"));
            phoneTextField = new JTextField();
            add(phoneTextField);
            add(new JLabel("Insurance Provider:"));
            insuranceProviderTextField = new JTextField();
            add(insuranceProviderTextField);
            add(new JLabel("Policy Number:"));
            policyNumberTextField = new JTextField();
            add(policyNumberTextField);
            add(new JLabel());
            createPatientButton = new JButton("Create Patient Account");
            createPatientButton.setFont(myFont);
            createPatientButton.addActionListener(listener);
            createPatientButton.setForeground(Color.WHITE);
            createPatientButton.setBackground(new Color(12, 35, 75));
            add(createPatientButton);
        }

        public void submit() {
            String name = nameTextField.getText();
            LocalDate dob = dobDatePicker.getDate();
            String address = addressTextField.getText();
            String phone = phoneTextField.getText();
            String insuranceProvider = insuranceProviderTextField.getText();
            String policyNumber = policyNumberTextField.getText();

            // Create a new patient objectM
            Patient patient = new Patient(name, dob, address, phone, insuranceProvider, policyNumber);

            // Add the patient to the patient map
            patientMap.put(name, patient);

            // Add the patient to the Patient class's list of patients
            Patient.addPatient(patient);

            // Update the table model in the viewPatientPanel
            viewPatientPanel.updateTable();

            // Reset the text fields
            nameTextField.setText("");
            dobDatePicker.setDateToToday();
            addressTextField.setText("");
            phoneTextField.setText("");
            insuranceProviderTextField.setText("");
            policyNumberTextField.setText("");

            JOptionPane.showMessageDialog(null, "Patient Account Created.");

            // Add the patient to the patient map
            System.out.println("Patient added to patientMap: " + patientMap.containsKey(name));

        }
    }

    public class ViewPatientPanel extends JPanel {
        private JTextField searchField;
        private JButton searchButton;
        private JTable table;
        private DefaultTableModel tableModel;
    
        public ViewPatientPanel() {
            // Create the search field and add it to the panel
            searchField = new JTextField(20);
            add(searchField);
    
            // Create the search button and add it to the panel
            searchButton = new JButton("Search");
            searchButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTable();
                }
            });
            add(searchButton);
    
            // Create the table and add it to the panel
            tableModel = new DefaultTableModel();
            tableModel.addColumn("Name");
            tableModel.addColumn("Date of Birth");
            tableModel.addColumn("Address");
            tableModel.addColumn("Phone Number");
            tableModel.addColumn("Insurance Provider");
            tableModel.addColumn("Policy Number");
            table = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            add(scrollPane);
    
            // Load initial data
            updateTable();
        }
    
        public void updateTable() {
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            model.setRowCount(0);
            String searchTerm = searchField.getText().toLowerCase();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            for (Patient patient : Patient.getPatients()) {
                if (patient.getName().toLowerCase().contains(searchTerm) ||
                    formatter.format(patient.getDateOfBirth()).contains(searchTerm) ||
                    patient.getAddress().toLowerCase().contains(searchTerm) ||
                    patient.getPhoneNumber().toLowerCase().contains(searchTerm) ||
                    patient.getInsuranceProvider().toLowerCase().contains(searchTerm) ||
                    patient.getPolicyNumber().toLowerCase().contains(searchTerm)) {
                    model.addRow(new Object[]{patient.getName(), formatter.format(patient.getDateOfBirth()), patient.getAddress(), patient.getPhoneNumber(), patient.getInsuranceProvider(), patient.getPolicyNumber()});
                }
            }
        }
    }

            public static void main(String[] args) {
                PatientManagementGUI gui = new PatientManagementGUI();
                gui.setVisible(true);
            }
}
