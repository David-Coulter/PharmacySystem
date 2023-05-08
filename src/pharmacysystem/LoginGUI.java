package pharmacysystem;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LoginGUI extends JFrame {
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton submitButton;
    private JPanel headerPanel;
    Font myFont = new Font("Proxima Nova", Font.PLAIN, 20);

    public LoginGUI() {
        // set up the main login GUI
        setTitle("Pharmacy Management System");
        setIconImage(new ImageIcon(getClass().getResource("/resources/ua.png")).getImage());
        setSize(400, 300);
        setLocationRelativeTo(null);

        // set up the header panel with an image
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/pharmacy.png"));
        JLabel header = new JLabel(icon);
        header.setPreferredSize(new Dimension(200, 50));
        headerPanel = new JPanel();
        headerPanel.add(header);
        headerPanel.setBackground(new Color(171, 5, 32));
        add(headerPanel, BorderLayout.NORTH);

        // add text fields for the username and password inputs
        loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.gridx = 0;
        c.gridy = 0;
        loginPanel.add(new JLabel("Username:"), c);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField(10);
        loginPanel.add(usernameField, c);
        c.gridx = 0;
        c.gridy = 1;
        loginPanel.add(new JLabel("Password:"), c);
        c.gridx = 1;
        c.gridy = 1;
        passwordField = new JPasswordField(10);
        loginPanel.add(passwordField, c);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        submitButton = new JButton("Submit");
        submitButton.setFocusable(false);
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(12, 35, 75));
        submitButton.setFont(myFont);
        submitButton.addActionListener(e -> login());
        loginPanel.add(submitButton, c);

        add(loginPanel, BorderLayout.CENTER);
    }

    private void login() {
        // validate username and password here
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();

        // check if the entered username and password match a user account
        if (username.equals("manager") && Arrays.equals(password, "manager1234".toCharArray())) {
        ManagerMenu managerMenu = new ManagerMenu();
        managerMenu.setVisible(true);
    } else if (username.equals("cashier") && Arrays.equals(password, "cashier1234".toCharArray())) {
        CashierMenu cashierMenu = new CashierMenu();
        cashierMenu.setVisible(true);
    } else if (username.equals("pharmacist") && Arrays.equals(password, "pharmacist1234".toCharArray())) {
        PharmacistMenu pharmacistMenu = new PharmacistMenu();
        pharmacistMenu.setVisible(true);
    } else {
        JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
    }

    }

    public static void main(String[] args) {
        LoginGUI loginGUI = new LoginGUI();
        loginGUI.setVisible(true);
    }
}
