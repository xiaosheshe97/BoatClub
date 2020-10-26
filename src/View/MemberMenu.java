package View;

import Model.*;
import Util.UserChoiceInBoatMenu;
import Util.UserChoiceInMemberMenu;
import java.util.Arrays;
import java.util.List;

public class MemberMenu extends menu{
    private String userInput;

    public void showInstruction() {
        System.out.println("Press 1 to show a compact list of members\n" +
                           "Press 2 to show a verbose list of members\n" +
                            "Press any key to go back to main menu");
    }

    //take user input and return a related enum value to use in member menu
    public UserChoiceInMemberMenu getUserInputInMemberMenu() {
        UserChoiceInMemberMenu choice = null;
        String input = userStringInput();
        switch (input) {
            case "1":
                choice = UserChoiceInMemberMenu.COMPACT_LIST;
                break;
            case "2":
                choice = UserChoiceInMemberMenu.VERBOSE_LIST;
                break;
            default:
                choice = UserChoiceInMemberMenu.QUIT;
                break;

        }
        return choice;
    }

    //assign choice of user in creating a new member  to a enum to use in controller
    public UserChoiceInMemberMenu getInputInCompactList() {
        UserChoiceInMemberMenu choice = null;

        switch(userInput){
            case "1":
                choice = UserChoiceInMemberMenu.DELETE;
                break;
            case "2":
                choice = UserChoiceInMemberMenu.UPDATE;
                break;
            case "3":
                choice = UserChoiceInMemberMenu.SPECIFIC_MEMBER;
                break;
            default:
                choice = UserChoiceInMemberMenu.QUIT;
                break;
        }
        return choice;
    }

    //show the compact list and return a member chose by user to do the desired request of user
    public Member showCompactList(BoatClub boatClub){
        int index =1;
        try {//if list is empty
            for (Member member : boatClub.getAllMembersLocally()) {
                System.out.println((index++) + ":This member name is : " + member.getName() +
                        "\nwith memberID of : " + member.getMemberID() +
                        "\nwhich has " + member.numberOfBoats() + "boat(s)" +
                        "\n------------\n");
            }
            System.out.println("Enter index of member to choose:\n" +
                    "Or press other integer to go back");
            int chosenMember = correctInteger();
            index = 1;
                for (Member member : boatClub.getAllMembersLocally()) {
                    if (index == chosenMember) {
                        System.out.println("Press 1 to delete a member\n" +
                                "Press 2 to update a member information\n" +
                                "Press 3 to see a specific member data\n" +
                                "Press any other key to go back");
                        userInput = userStringInput();
                        return member;
                    } else {
                        index++;
                    }
                } System.out.println("This member does not exist you will go back to start menu\n");
                goBackToStartMenu();
            

        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        userInput="";//to not get null error in getInputInCompactList()
        return null;
    }

    //show in detail list of members
    public void showVerboseList(BoatClub boatClub){
        try {// if list is empty
            for(Member member : boatClub.getAllMembersLocally()){
                System.out.println("------------------------");
                showMemberInformation(member);
                System.out.println("\n----------------------\n");
            }
            goBackToStartMenu();
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
    }

    // show update menu of member
    public void showUpdateMemberMenu(Member member){
        boolean isValid = false;
        System.out.println("Enter new name");
        String name = userStringInput();


        do {
            System.out.println("Enter new 10 digits personal number");
             long personalNumber = correctLong();
             try {
                 PersonalNumber personalNumberEntered = new PersonalNumber(""+personalNumber);
                 member.updateMemberInformation(name,personalNumberEntered);
                 isValid = true;
             }catch (IllegalArgumentException ex){
                 System.out.println(ex.getMessage());
             }
        }while (!isValid);
    }

    // show deleted member confirmation
    public void showDeletedMemberConfirmationMsg(Member member){
        System.out.println(member.getName() + " is deleted\n");
        goBackToStartMenu();
    }

    public void showUpdatedMemberConfirmationMsg(Member member){
        System.out.println(member.getName() + " is updated\n");
        goBackToStartMenu();
    }

    public void showMemberInformation(Member member) {
        System.out.println("This member name is : " + member.getName() +
                "\nwith personal number of " + member.changeToStringPersonalID() +
                "\nwith memberID of " + member.getMemberID());
        System.out.println("This member has " + member.numberOfBoats() + "boats");
        int index=1;
        if(member.numberOfBoats() != 0)
            System.out.println("this member boat information is :");
        for (Boat boat : member.boatsOwnedByMember()) {
            System.out.println((index++) + " - Boat type :" + boat.getType() +
                    ", Boat Length : " + boat.getLength());
        }
    }

    //if boat doesnt exist on the list it return false
    public boolean askUserForChooseAnOptionInBoatMenu(Member member){

        if(member.numberOfBoats()==0) {
            System.out.println("Press 1 to register a new boat\n" +
                    "Or press any key to go back to main menu");
            userInput = userStringInput();
            if (!userInput.equalsIgnoreCase("1"))
                return true;
        }
        else {
            System.out.println("Press 1 to register a bew boat\n" +
                    "Press 2 to update the boat information\n" +
                    "Press 3 to delete the boat\n" +
                    "Press any other key to go back");
            userInput = userStringInput();
            //return boat;
            List<String> options = Arrays.asList("1" , "2" , "3");

            if (!options.contains(userInput) )
                return true;
        }
        return false;
    }

    //return the related enum of user input to hadle in boat menu
    public UserChoiceInBoatMenu getUserInputInBoatMenu(){
        UserChoiceInBoatMenu choice = null;
        switch (userInput){
            case "1":
                choice = UserChoiceInBoatMenu.ADD_NEW_BOAT;
                break;
            case "2":
                choice = UserChoiceInBoatMenu.CHANGE_BOAT_INFORMATION;
                break;
            case "3":
                choice = UserChoiceInBoatMenu.DELETE_BOAT;
                break;
            default:
                choice = UserChoiceInBoatMenu.GO_BACK;
        }
        return choice;
    }

    //show the menu of registering a new boat and return the boat
    public Boat showRegisterNewBoatMenu(Member member){
        Boat boat = null;
        do {
            System.out.println("Please enter length of the boat :");
            double boatLength = correctDouble();
            System.out.println("Please enter boat type(1 for sailboat, 2 for motor sail, " +
                    "3 for kayak/canoe, and 4 for others)");
            BoatType type = correctBoatType();
            try {
                boat = new Boat(type , boatLength);
                member.registerANewBoat(boat);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }while(boat == null);
        return boat;
    }

    public void showAddedBoatConfirmation(Boat boat){
        System.out.println(boat.getType()+" is added\n");
        goBackToStartMenu();
    }

    public void askUserToUpdateBoatData(Boat boat){
        boolean isValid = false;
        do {
            System.out.println("Please enter length of the boat :");
            double boatLength = correctDouble();
            System.out.println("Please enter boat type(1 for sailboat, 2 for motor sail, " +
                    "3 for kayak/canoe, and 4 for others)");
            BoatType type = correctBoatType();
            try {
                boat.setType(type);
                boat.setLength(boatLength);
                isValid = true;
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }while(!isValid);
    }

    private void showBoatsOfMember(Member member){
        int index = 1;
        for(Boat boat : member.boatsOwnedByMember()){
            System.out.println((index++) + " Boat type : " + boat.getType() + " boat length : " +
                    boat.getLength());
        }
    }

    public Boat showDeleteOrChangeABoat(Member member , UserChoiceInBoatMenu choice){
        Boat boatFound = null;
            showBoatsOfMember(member);
            System.out.println("Enter index of boat to choose or any other key to go back to last menu:");
            int chosenMember = super.correctInteger();
             int index = 1;
            for(Boat boat : member.boatsOwnedByMember()){//when we iterate on a loop we can't remover or add an element
                if (index == chosenMember) {
                    boatFound = boat;
                } else{
                        index++;
                    }
                }
        try {
           boatFound = member.memberSelectABoat(boatFound);// to check if it s null or not(do u have any  better idea how to handle it
            if(choice == UserChoiceInBoatMenu.CHANGE_BOAT_INFORMATION)
                askUserToUpdateBoatData(boatFound);
            else if(choice == UserChoiceInBoatMenu.DELETE_BOAT)
                member.deleteBoat(boatFound);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        return boatFound;
    }

    public void showUpdatedBoatConfirmation(Boat boat){
        System.out.println(boat.getType() + " is updated");
        goBackToStartMenu();
    }

    public void showDeletedBoatConfirmation(Boat boat){
        System.out.println(boat.getType() + " is deleted");
        goBackToStartMenu();
    }


}
