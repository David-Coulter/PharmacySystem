package pharmacysystem;

public class Pharmacy {
    private String name;
    private String websiteUrl;
    private String owner;
    private String phoneNumber;
    private String hoursOfOperation;
    private String socialMediaHandle;

    public Pharmacy(String name, String websiteUrl, String owner, String phoneNumber, String hoursOfOperation, String socialMediaHandle) {
        this.name = name;
        this.websiteUrl = websiteUrl;
        this.owner = owner;
        this.phoneNumber = phoneNumber;
        this.hoursOfOperation = hoursOfOperation;
        this.socialMediaHandle = socialMediaHandle;
    }
    

    // Getters and setters for all fields

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public String getSocialMediaHandle() {
        return socialMediaHandle;
    }

    public void setSocialMediaHandle(String socialMediaHandle) {
        this.socialMediaHandle = socialMediaHandle;
    }
}
