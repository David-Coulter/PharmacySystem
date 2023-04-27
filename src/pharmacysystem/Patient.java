package pharmacysystem;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;


public class Patient {
	public static List<Patient> getPatients() {
        List<Patient> patients = new ArrayList<>();

        // create and add mock patients here
        patients.add(new Patient("John Doe", LocalDate.of(1980, 1, 1), "123 Main St", "555-1234", "Blue Cross", "12345"));
        patients.add(new Patient("Jane Smith", LocalDate.of(1990, 5, 10), "456 Oak Ave", "555-5678", "Aetna", "67890"));
        patients.add(new Patient("Bob Johnson", LocalDate.of(1975, 9, 15), "789 Maple Blvd", "555-9012", "Cigna", "34567"));
        
        return patients;

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
