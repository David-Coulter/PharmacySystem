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
        this.prescriptions.put("Lisinopril", new Prescription("Lisinopril",LocalDate.of(2024, 8, 31), 10.00, 50));
        this.prescriptions.put("Levothyroxine", new Prescription("Levothyroxine",LocalDate.of(2023, 7, 31), 20.00, 30));
        this.prescriptions.put("Atorvastatin", new Prescription("Atorvastatin",LocalDate.of(2025, 1, 31), 15.00, 40));
        this.prescriptions.put("Metformin", new Prescription("Metformin", LocalDate.of(2023, 11, 30), 8.00, 60));
        this.prescriptions.put("Amlodipine", new Prescription("Amlodipine", LocalDate.of(2023, 12, 31), 12.00, 20));
        this.prescriptions.put("Omeprazole", new Prescription("Omeprazole", LocalDate.of(2022, 10, 31), 18.00, 10));
        this.prescriptions.put("Losartan", new Prescription("Losartan", LocalDate.of(2025, 2, 28), 11.00, 25));
        this.prescriptions.put("Sertraline", new Prescription("Sertraline", LocalDate.of(2023, 9, 30), 25.00, 35));
        this.prescriptions.put("Simvastatin", new Prescription("Simvastatin", LocalDate.of(2022, 6, 30), 14.00, 45));
        this.prescriptions.put("Montelukast", new Prescription("Montelukast", LocalDate.of(2024, 4, 30), 17.00, 15));
        this.prescriptions.put("Ibuprofen", new Prescription("Ibuprofen", LocalDate.of(2023, 10, 31), 5.00, 20));
        this.prescriptions.put("Amoxicillin", new Prescription("Amoxicillin", LocalDate.of(2022, 12, 31), 7.00, 30));
    }

    public void addItem(String name, LocalDate expirationDate, double cost, int quantity) {
        this.prescriptions.put(name, new Prescription(name, expirationDate, cost, quantity));
    }

    public Prescription getPrescription(String name) {
        return this.prescriptions.get(name);
    }

    public String getName(String name) {
        Prescription prescription = this.prescriptions.get(name);
        return prescription != null ? prescription.getName() : null;
    }    

    public Map<String, Prescription> getPrescriptions() {
        return this.prescriptions;
    }

    public static class Prescription {
        private String name;
        private LocalDate expirationDate;
        private double cost;
        private int quantity;
    
        public Prescription(String name, LocalDate expirationDate, double cost, int quantity) {
            this.name = name;
            this.expirationDate = expirationDate;
            this.cost = cost;
            this.quantity = quantity;
        }
    
        public String getName() {
            return this.name;
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
