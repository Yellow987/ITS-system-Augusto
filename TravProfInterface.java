package Proj1a;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class TravProfInterface {
    private String userID;
    private TravProfDB travProfDB;

    TravProfInterface(String userID) {
        this.userID = userID;
        this.travProfDB = new TravProfDB("temp.txt");
    }

    public Boolean getUserChoice() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please choose the number corresponding to your selection");
        System.out.println("1. Create new profile");
        System.out.println("2. Edit existing profile");
        System.out.println("3. Delete profile");
        System.out.println("4. Search Profiles");
        System.out.println("5. Display all profiles");
        System.out.println("6. save database");
        System.out.println("7. Exit ITS");
        int choice = getChoice("", 7);

        switch (choice) {
            case 1: //done
                createNewTravProf();
                break;
            case 2: //done
                this.updateTravProf();
                break;
            case 3: //done
                this.deleteTravProf(selectSpecificTravProf());
                break;
            case 4: //done
                this.findTravProfHelper();
                break;
            case 5: //done
                this.displayAllTravProf();
                break;
            case 6:
                this.writeToDB();
                break;
            case 7: //done
                new File("temp.txt").delete();
                return false;
        }
        return true;
    }

    private ArrayList<TravProf> findTravProfHelper() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the Last Name:");
        String lastName = scan.nextLine();
        return this.findTravProf(lastName);
    }
    private void deleteTravProf(TravProf prof){
        this.travProfDB.deleteProfile(prof);
        System.out.println("Profile Deleted!");
    }
    private ArrayList<TravProf> findTravProf(String lastName){
        ArrayList<TravProf> profs = this.travProfDB.findProfile(lastName, this.userID);
        for (int i = 0; i < profs.size(); i++){
            System.out.println(i + ". ");
            profs.get(i).printTravProf();
        }
        return profs;
    }
    private TravProf selectSpecificTravProf(){
        ArrayList<TravProf> profs = this.findTravProfHelper();
        if (profs.size() == 1) return profs.get(0);
        int choice = getChoice("Select a profile by number: ", profs.size());
        return profs.get(choice);
    }

    private void updateTravProf(){
        Scanner scan = new Scanner(System.in);

        TravProf profile = this.selectSpecificTravProf();
        deleteTravProf(profile);
        profile.printTravProf();
        System.out.println("Please enter the number of the data you would like to change.");
        System.out.println("1. Address");
        System.out.println("2. Phone");
        System.out.println("3. Travel Type");
        System.out.println("4. Trip Cost");
        System.out.println("5. Payment Type");
        System.out.println("6. Doctor Name");
        System.out.println("7. Doctor Phone");
        System.out.println("8. Illness");
        System.out.println("9. Allergies");

        int choice = getChoice("invalid choice", 9);
        System.out.println("New value:");
        switch(choice){
            case 1: profile.updateAddress(scan.nextLine()); break;
            case 2: profile.updatePhone(scan.nextLine()); break;
            case 3: profile.updateTravelType(this.getTravelType());
            case 4: profile.updateTripCost(this.getTripCost()); break;
            case 5: profile.updatePaymentType(this.getPaymentType()); break;
            case 6:
                MedCond medCond = profile.getMedCondInfo();
                medCond.updateMdContact(scan.nextLine());
                profile.updateMedCondInfo(medCond); break;
            case 7:
                MedCond medCond = profile.getMedCondInfo();
                medCond.updateMdPhone(scan.nextLine());
                profile.updateMedCondInfo(medCond); break;
            case 8:
                MedCond medCond = profile.getMedCondInfo();
                medCond.updateIllType(this.getIllType());
                profile.updateMedCondInfo(medCond); break;
            case 9:
                MedCond medCond = profile.getMedCondInfo();
                medCond.updateAlgType(this.getAlgType());
                profile.updateMedCondInfo(medCond); break;
        }
        this.travProfDB.insertNewProfile(profile);
    }

    private void displayAllTravProf(){
        TravProf nextProf = this.travProfDB.findNextProfile();
        while (nextProf != null){
            nextProf.printTravProf();
            System.out.println("");
            nextProf = this.travProfDB.findNextProfile();
        }
    }

    private void writeToDB(){
        Scanner scan = new Scanner(System.in);
        System.out.println("File name to save to? : ");
        this.travProfDB.writeAllTravProf(scan.nextLine());
    }

    private void initDB(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Database to initialize? : ");
        this.travProfDB.initializeDataBase(scan.nextLine());
    }

    private float getTripCost(){
        Scanner scan = new Scanner(System.in);
        float tripCost;
        while(true) {
            System.out.println("TripCost:");
            if (!scan.hasNextFloat()) {
                System.out.println("invalid number");
                scan.nextLine();
            }
            else {
                tripCost = scan.nextFloat();
                break;
            }
        }
        return tripCost;
    }
    private String getPaymentType(){
        String paymentType = "";
        int paymentTypeChoice = getChoice("Payment Type: (1) Credit (2) Check (3) Debit (4) Invoice", 4);
        switch (paymentTypeChoice){
            case 1: paymentType = "Credit"; break;
            case 2: paymentType = "Check"; break;
            case 3: paymentType = "Debit"; break;
            case 4: paymentType = "Invoice"; break;
        }
        return paymentType;
    }
    private String getTravelType(){
        String travelType = "";
        int travelTypeChoice = getChoice("Travel Type: (1) Business, (2) Pleasure", 2);
        switch (travelTypeChoice){
            case 1: travelType = "Business"; break;
            case 2: travelType = "Pleasure"; break;
        }
        return  travelType;
    }
    private String getAlgType(){
        String allergies = "";
        int allergiesChoice = getChoice("Allergies: (1) None (2) Food (3) Medication (4) Other", 4);
        switch (allergiesChoice){
            case 1: allergies = "None"; break;
            case 2: allergies = "Food"; break;
            case 3: allergies = "Medication"; break;
            case 4: allergies = "Other"; break;
        }
        return allergies;
    }
    private String getIllType(){
        String illness = "";
        int illnessChoice = getChoice("Illness: (1) None (2) Heart (3) Diabetes (4) Asthma (5) Other", 5);
        switch (illnessChoice){
            case 1: illness = "None"; break;
            case 2: illness = "Heart"; break;
            case 3: illness = "Diabetes"; break;
            case 4: illness = "Asthma"; break;
            case 5: illness = "Other"; break;
        }
        return illness;
    }

    private TravProf createNewTravProf(){
        Scanner scan = new Scanner(System.in);
        System.out.println("First Name:");
        String fName = scan.nextLine();
        System.out.println("Last Name:");
        String lname = scan.nextLine();
        System.out.println("Address:");
        String address = scan.nextLine();
        System.out.println("Phone:");
        String phone = scan.nextLine();
        float tripCost = this.getTripCost();
        String travelType = this.getTravelType();
        String paymentType = this.getPaymentType();
        MedCond medCond = createNewMedCond();
        TravProf profile = new TravProf(this.userID, fName, lname, address, phone, tripCost, travelType, paymentType, medCond);
        this.travProfDB.insertNewProfile(profile);
        profile.printTravProf();
        return profile;
    }

    private int getChoice(String options, int max){
        Scanner scan = new Scanner(System.in);
        int choice = -1;
        while (true){
            System.out.println(options);
            if (!scan.hasNextInt()){
                System.out.println("invalid choice");
                scan.nextLine();
            }
            else {
                choice = scan.nextInt();
                if (choice > max) {
                    System.out.println("invalid choice");
                    scan.nextLine();
                }
                else{
                    break;
                }
            }
        }
        return choice;
    }

    private MedCond createNewMedCond(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Doctor Name:");
        String dName = scan.nextLine();
        System.out.println("Doctor Phone Number:");
        String phone = scan.nextLine();
        String allergies = this.getAlgType();
        String illness = this.getIllType();
        return new MedCond(dName, phone, allergies, illness);
    }

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to ITS");
        System.out.println("Please enter your ITS ID: ");
        TravProfInterface TPInterface = new TravProfInterface(scan.nextLine());

        Boolean shouldContinue = true;
        while (shouldContinue) {
            shouldContinue = TPInterface.getUserChoice();
        }
    }
}
