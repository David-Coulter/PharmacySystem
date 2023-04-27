package pharmacysystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Inventory {
    private Map<String, Prescription> prescriptions;

    public Inventory() {
        this.prescriptions = new HashMap<>();
        // add top 10 most popular prescriptions
        this.prescriptions.put("Lisinopril", new Prescription(LocalDate.of(2024, 8, 31), 10.00, 50));
        this.prescriptions.put("Levothyroxine", new Prescription(LocalDate.of(2023, 7, 31), 20.00, 30));
        this.prescriptions.put("Atorvastatin", new Prescription(LocalDate.of(2025, 1, 31), 15.00, 40));
        this.prescriptions.put("Metformin", new Prescription(LocalDate.of(2023, 11, 30), 8.00, 60));
        this.prescriptions.put("Amlodipine", new Prescription(LocalDate.of(2023, 12, 31), 12.00, 20));
        this.prescriptions.put("Omeprazole", new Prescription(LocalDate.of(2022, 10, 31), 18.00, 10));
        this.prescriptions.put("Losartan", new Prescription(LocalDate.of(2025, 2, 28), 11.00, 25));
        this.prescriptions.put("Sertraline", new Prescription(LocalDate.of(2023, 9, 30), 25.00, 35));
        this.prescriptions.put("Simvastatin", new Prescription(LocalDate.of(2022, 6, 30), 14.00, 45));
        this.prescriptions.put("Montelukast", new Prescription(LocalDate.of(2024, 4, 30), 17.00, 15));
    }

    public void addItem(String name, LocalDate expirationDate, double cost, int quantity) {
        this.prescriptions.put(name, new Prescription(expirationDate, cost, quantity));
    }

    public Prescription getPrescription(String name) {
        return this.prescriptions.get(name);
    }

    public Map<String, Prescription> getPrescriptions() {
        return this.prescriptions;
    }

    public static class Prescription {
        private LocalDate expirationDate;
        private double cost;
        private int quantity;

        public Prescription(LocalDate expirationDate, double cost, int quantity) {
            this.expirationDate = expirationDate;
            this.cost = cost;
            this.quantity = quantity;
        }

        public LocalDate getExpirationDate() {
            return this.expirationDate;
        }

        public double getCost() {
            return this.cost;
        }

        public int getQuantity() {
            return this.quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
    public JTable getInventoryTable() {
        String[] columnNames = {"Prescription Name", "Expiration Date", "Cost", "Quantity"};
        Object[][] data = new Object[getPrescriptions().size()][columnNames.length];

        int i = 0;
        for (Map.Entry<String, Prescription> entry : getPrescriptions().entrySet()) {
            Prescription prescription = entry.getValue();
            data[i][0] = prescription.getClass();
            data[i][1] = prescription.getExpirationDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            data[i][2] = prescription.getCost();
            data[i][3] = prescription.getQuantity();
            i++;
        }

        return new JTable(new DefaultTableModel(data, columnNames));
    }

}
