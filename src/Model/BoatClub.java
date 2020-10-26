package Model;

import Model.searchRule.ISearchingStrategy;

import java.util.ArrayList;

public class BoatClub {
    private ArrayList<Member> members=new ArrayList<>();
    private Registry registry = new Registry();
    private Member member;
    private boolean isLoggedIn;
    private ISearchingStrategy searchMethod;


    public BoatClub(ISearchingStrategy searchMethod) {
        this.searchMethod = searchMethod;
        isLoggedIn = false;
    }

    //we might use later it create a member and return it
    public Member creatMember(String userName, String personalNumber) {
        Member member = new Member(userName, personalNumber);
        return member;
    }

    //add new member to list of members in boat club
    public Member addNewMember(Member member){
        members.add(member);
        return member;
    }

    //this used in registery to create them from the loaded file and add them to list
    public Member makeMemberForLoadingInStartOfProgram(String name , PersonalNumber personalNemuber , String memberID){
        Member member = new Member(name , personalNemuber, memberID);
        members.add(member);
        return member;
    }

    //delete memeber if list is not empty
    public Member deleteMember(Member member){
        this.member = member;
        if(members==null)
            throw new IllegalArgumentException("List is empty");
        this.members.remove(member);
        //I used here to return that member who was deleted
        return this.member;
    }

    //we load all information with this method once we start the program
    public void getAllMembersFromRegistry(){
        this.members= (ArrayList<Member>) registry.loadFromSavedFile(this,registry.verboseList("SaveFile.txt"));
    }

    //we save once user choose the save the program in main menu
    public void save(){
        Registry registry = new Registry();
        registry.updateRegistryFile(this);
    }

    //enter a member name to get member
    public Member getMember(String memberName){
        for (Member m:members){
            if (memberName.equals(memberName)){
                return m;
            }
        }
        return null;
    }

    public void searchForMembers(String input){
        searchMethod.searchMembers(this,input);
    }

    //other than first time, we use this one to get information of arrayList
    public Iterable<Member> getAllMembersLocally(){
        if(this.members.isEmpty())
            throw new IllegalArgumentException("List is empty\n" +
                    "----------------");
        return this.members;
    }
    public boolean getIsLoggedIn() {
        return isLoggedIn;
    }


    public void checkLogin(Member member){
            if (member.getName().equals("1111")&&member.getPassword().equals("1111")){
                isLoggedIn = true;

        }
    }
}
