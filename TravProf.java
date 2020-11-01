package Proj1a;

public class TravProf {
    private String travAgentID;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private float tripCost;
    private String travelType;
    private String paymentType;
    private MedCond medCondInfo;

    public TravProf(String travAgentID, String firstName, String lastName,
                    String address, String phone, float tripCost, String travelType,
                    String paymentType, MedCond medCondInfo){
        this.travAgentID = travAgentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.tripCost = tripCost;
        this.travelType = travelType;
        this.paymentType = paymentType;
        this.medCondInfo = medCondInfo;
    }

    public void printTravProf(){
        System.out.println("First Name: " + this.firstName);
        System.out.println("Last Name: " + this.lastName);
        System.out.println("Address: " + this.address);
        System.out.println("Phone: " + this.phone);
        System.out.println("UserID: " + this.travAgentID);
        System.out.println("Travel Type" + this.travelType);
        System.out.println("Trip Cost" + this.tripCost);
        System.out.println("Payment Type: " + this.paymentType);
        this.medCondInfo.printMedCond();
    }

    public String getTravAgentID() {
        return travAgentID;
    }

    public String getAddress() {
        return address;
    }

    public float getTripCost() {
        return tripCost;
    }

    public String getFirstName() {
        return firstName;
    }

    public MedCond getMedCondInfo() {
        return medCondInfo;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getPhone() {
        return phone;
    }

    public String getTravelType() {
        return travelType;
    }

    public void updateAddress(String address) {
        this.address = address;
    }

    public void updateFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void updateLastName(String lastName) {
        this.lastName = lastName;
    }

    public void updateMedCondInfo(MedCond medCondInfo) {
        this.medCondInfo = medCondInfo;
    }

    public void updatePaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public void updateTravelType(String travelType) {
        this.travelType = travelType;
    }

    public void updateTripCost(float tripCost) {
        this.tripCost = tripCost;
    }
}
