package pharmacysystem;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class Patient {
    private static List<Patient> patients;

    static {
        patients = new ArrayList<>();
        patients.add(new Patient("John Doe", LocalDate.of(1980, 1, 1), "123 Main St", "555-1234", "Blue Cross", "12345"));
        patients.add(new Patient("Jane Smith", LocalDate.of(1990, 5, 10), "456 Oak Ave", "555-5678", "Aetna", "67890"));
        patients.add(new Patient("Bob Johnson", LocalDate.of(1975, 9, 15), "789 Maple Blvd", "555-9012", "Cigna", "34567"));
        patients.add(new Patient("Alice Brown", LocalDate.of(1985, 2, 20), "567 Elm St", "555-2345", "Blue Cross", "23456"));
        patients.add(new Patient("David Lee", LocalDate.of(1978, 7, 7), "345 Pine Ave", "555-6789", "Aetna", "78901"));
        patients.add(new Patient("Samantha Wong", LocalDate.of(1995, 12, 12), "901 Cedar Rd", "555-3456", "Cigna", "45678"));
        patients.add(new Patient("James Kim", LocalDate.of(1982, 4, 4), "234 Birch Ln", "555-7890", "Blue Cross", "89012"));
        patients.add(new Patient("Karen Chen", LocalDate.of(1973, 8, 8), "678 Oakwood Dr", "555-2345", "Aetna", "56789"));
        patients.add(new Patient("Michael Johnson", LocalDate.of(1987, 6, 6), "890 Cherry St", "555-6789", "Cigna", "12356"));
        patients.add(new Patient("Emily Davis", LocalDate.of(1992, 3, 3), "432 Walnut Ave", "555-9012", "Blue Cross", "34568"));
        }
        

    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String phoneNumber;
    private String insuranceProvider;
    private String policyNumber;

    public Patient(String name, LocalDate dateOfBirth, String address, String phoneNumber, String insuranceProvider, String policyNumber) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.insuranceProvider = insuranceProvider;
        this.policyNumber = policyNumber;
    }

    public static List<Patient> getPatients() {
        return patients;
    }

    public static void addPatient(Patient patient) {
        patients.add(patient);
    }

    // getters and setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInsuranceProvider() {
        return insuranceProvider;
    }

    public void setInsuranceProvider(String insuranceProvider) {
        this.insuranceProvider = insuranceProvider;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
    
}
