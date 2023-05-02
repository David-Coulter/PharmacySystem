package pharmacysystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.time.LocalDate;


public class PatientManagementGUI extends JFrame implements ActionListener {
    private JButton createPatientButton, viewPatientButton;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private CreatePatientPanel createPatientPanel;
    private ViewPatientPanel viewPatientPanel;
    private HashMap<String, Patient> patientMap;

    public PatientManagementGUI() {
        super("Patient Management");
        
        createPatientButton = new JButton("Create Patient");
        createPatientButton.addActionListener(this);
        
        viewPatientButton = new JButton("View Patient");
        viewPatientButton.addActionListener(this);
        
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(createPatientButton);
        buttonPanel.add(viewPatientButton);
        
        // Set up panels
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        createPatientPanel = new CreatePatientPanel(this);
        viewPatientPanel = new ViewPatientPanel(this);
        mainPanel.add(createPatientPanel, "createPatient");
        mainPanel.add(viewPatientPanel, "viewPatient");
        add(mainPanel);
        
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Set up frame
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600,400);
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
            add(new JLabel(""));
            createPatientButton = new JButton("Create Patient Account");
            createPatientButton.addActionListener(listener);
            add(createPatientButton);
        }

        public void submit() {
            String name = nameTextField.getText();
            LocalDate dob = dobDatePicker.getDate();
            String address = addressTextField.getText();
            String phone = phoneTextField.getText();
            String insuranceProvider = insuranceProviderTextField.getText();
            String policyNumber = policyNumberTextField.getText();

            // Create a new patient object
            Patient patient = new Patient(name, dob, address, phone, insuranceProvider, policyNumber);

            // Add the patient to the patient map
            patientMap.put(name, patient);

            // Add the patient to the Patient class's list of patients
            Patient.getPatients().add(patient);

            // Reset the text fields
            nameTextField.setText("");
            dobDatePicker.setDateToToday();
            addressTextField.setText("");
            phoneTextField.setText("");
            insuranceProviderTextField.setText("");
            policyNumberTextField.setText("");
        }
    }

    public class ViewPatientPanel extends JPanel {
        private JComboBox<String> nameComboBox;
        private JTextField dobTextField;
        private JTextField addressTextField;
        private JTextField phoneTextField;
        private JTextField insuranceProviderTextField;
        private JTextField policyNumberTextField;
        private JTextArea purchaseHistoryTextArea;

        private Map<String, Patient> patientMap;

        public ViewPatientPanel(ActionListener listener) {
            patientMap = new HashMap<>();
            List<Patient> patients = Patient.getPatients();
            for (Patient patient : patients) {
                patientMap.put(patient.getName(), patient);
            }

            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();

            c.gridx = 0;
            c.gridy = 0;
            c.anchor = GridBagConstraints.WEST;
            add(new JLabel("Name:"), c);

            c.gridx = 1;
            c.gridy = 0;
            nameComboBox = new JComboBox<>(patients.stream().map(Patient::getName).toArray(String[]::new));
            nameComboBox.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = (String) nameComboBox.getSelectedItem();
                    Patient patient = patientMap.get(name);
                    if (patient != null) {
                        dobTextField.setText(patient.getDateOfBirth().toString());
                        addressTextField.setText(patient.getAddress());
                        phoneTextField.setText(patient.getPhoneNumber());
                        insuranceProviderTextField.setText(patient.getInsuranceProvider());
                        policyNumberTextField.setText(patient.getPolicyNumber());
                    }
                }
            });
            add(nameComboBox, c);

            c.gridx = 0;
            c.gridy = 1;
            add(new JLabel("Date of Birth: "), c);

            c.gridx = 1;
            c.gridy = 1;
            dobTextField = new JTextField();
            dobTextField.setPreferredSize(new Dimension(200, 30));
            dobTextField.setEditable(false);
            add(dobTextField, c);

            c.gridx = 0;
            c.gridy = 2;
            add(new JLabel("Address: "), c);

            c.gridx = 1;
            c.gridy = 2;
            addressTextField = new JTextField();
            addressTextField.setPreferredSize(new Dimension(200, 30));
            addressTextField.setEditable(false);
            add(addressTextField, c);

            c.gridx = 0;
            c.gridy = 3;
            add(new JLabel("Phone Number: "), c);

            c.gridx = 1;
            c.gridy = 3;
            phoneTextField = new JTextField();
            phoneTextField.setPreferredSize(new Dimension(200, 30));
            phoneTextField.setEditable(false);
            add(phoneTextField, c);

            c.gridx = 0;
            c.gridy = 4;
            add(new JLabel("Insurance Provider: "), c);

            c.gridx = 1;
            c.gridy = 4;
            insuranceProviderTextField = new JTextField();
            insuranceProviderTextField.setPreferredSize(new Dimension(200, 30));
            insuranceProviderTextField.setEditable(false);
            add(insuranceProviderTextField, c);

            c.gridx = 0;
            c.gridy = 5;
            add(new JLabel("Policy Number: "), c);

            c.gridx = 1;
            c.gridy = 5;
            policyNumberTextField = new JTextField();
            policyNumberTextField.setPreferredSize(new Dimension(200, 30));
            policyNumberTextField.setEditable(false);
            add(policyNumberTextField, c);

            c.gridx = 0;
            c.gridy = 6;
            add(new JLabel("Purchase History: "), c);

            c.gridx = 1;
            c.gridy = 6;
            purchaseHistoryTextArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(purchaseHistoryTextArea);
            scrollPane.setPreferredSize(new Dimension(300, 100));
            add(scrollPane, c);
        }
    }

            public static void main(String[] args) {
                PatientManagementGUI gui = new PatientManagementGUI();
                gui.setVisible(true);
            }
}
