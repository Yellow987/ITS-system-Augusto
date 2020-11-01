package Proj1a;

public class MedCond {
    private String mdContact;
    private String mdPhone;
    private String algType;
    private String illType;

    public MedCond(String mdContact, String mdPhone, String algType, String illType){
        this.mdContact = mdContact;
        this.mdPhone = mdPhone;
        this.algType = algType;
        this.illType = illType;
    }

    public void printMedCond(){
        System.out.println("Doctor Name: " + this.mdContact);
        System.out.println("Doctor Phone: " + this.mdPhone);
        System.out.println("Allergies: " + this.algType);
        System.out.println("Illness: " + this.illType);
    }

    public String getMdContact(){
        return this.mdContact;
    }

    public String getMdPhone(){
        return this.mdPhone;
    }

    public String getAlgType(){
        return this.algType;
    }

    public String getIllType(){
        return this.illType;
    }

    public void updateMdContact(String newMdContact){
        this.mdContact = newMdContact;
    }

    public void updateMdPhone(String newMdPhone){
        this.mdPhone = newMdPhone;
    }

    public void updateAlgType(String newAlgType){
        this.algType = newAlgType;
    }

    public void updateIllType(String newIllType){
        this.illType = newIllType;
    }
}