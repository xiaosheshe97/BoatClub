package Model;

import java.util.ArrayList;

public class Member {
    private String name;
    private PersonalNumber personalNumber;
    private int numbersOfBoatsOwnByAMember;
    private String memberID ;
    private ArrayList<Boat> boats = new ArrayList<>();
    private String password;

    //Constructor
    public Member(String name, PersonalNumber personalNumber) {
        setName(name);
        this.personalNumber = personalNumber;
        this.memberID = creatUniqueID();
    }



    //Constructor
    public Member(String name , PersonalNumber personalNumber,String memberID){
        setName(name);
        this.personalNumber = personalNumber;
        setMemberID(creatUniqueID());
    }

    public Member(String name, String password) {
        this.name = name;
        this.password = password;

    }

    // for updating member
    public void updateMemberInformation(String name, PersonalNumber personalNumber) {
        setName(name);
        this.personalNumber = personalNumber;
    }

    // return name
    public String getName() { return name; }

    //set name
    public void setName(String name) { this.name = name; }

    //return personal number
    public PersonalNumber getPersonalNumber() { return personalNumber; }

    //set personal number if it s not valid it will throws error
    public void setPersonalNumber(String personalNumber) {

        PersonalNumber personalNumberEntered = new PersonalNumber(personalNumber);
        /*if(!personalNumberEntered.validID(personalNumber)) {
            throw new IllegalArgumentException("Personal number should be a 10 digit number\n");
        }*/
        this.personalNumber = personalNumberEntered;
    }

    //check if personal number is digit and length is 10
    private boolean isValid(String input){
        return input.length() == 10 && input.matches("-?\\d+(\\.\\d+)?");
        //check its digits
    }

    //this used when we load from file and want to assign the same member Id for same person
    public String getMemberID() {
        return memberID;
    }

    private String creatUniqueID(){
        //use current time to creat a unique id
        //only take long from 8 to 12
        long ID = System.currentTimeMillis();
        return Long.toString(ID).substring(9,13);
    }

    //add new boat to boats own by member
    public void registerANewBoat(Boat boat){boats.add(boat);}

    //we set member ID of member in registry file
    public void setMemberID(String memberID){ this.memberID=memberID; }

    //return an iterable of memebrs so user has no access to list
    public Iterable<Boat> boatsOwnedByMember(){ return this.boats; }

   // public void setBoats(ArrayList<Boat> boats) {
     //   this.boats = boats;
    //}

    //this used in registery file
    public void setNumbersOfBoatsOwnByAMember(int numbersOfBoats){
        this.numbersOfBoatsOwnByAMember=numbersOfBoats;
    }

    //numbers of boats own by member
    public int numberOfBoats(){
        return this.boats.size();
    }

    public void registerNewBoat(BoatType boatType, double length){
        Boat boat = new Boat(boatType, length);
        boats.add(boat);
    }

    //if boat beolng to person it return the boat
    public Boat memberSelectABoat(Boat boat){
        if(boat == null)
            throw new IllegalArgumentException("Boat could not be found");
        return boat;
    }

    //remove the boat form boats own by member
    public void deleteBoat(Boat boat) {
        boats.remove(boat);
    }

    //we might use it later
    public void updateBoatData(Boat boat , double length , BoatType type){
        if(boat == null)
            throw new IllegalArgumentException("This boat does not exist you will go back to menu");
        boat.setLength(length);
        boat.setType(type);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String changeToStringPersonalID(){
        return this.personalNumber.getPersonalNumber();
    }

}
