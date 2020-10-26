package Model.searchRule;

import Model.BoatClub;
import Model.Member;

import java.util.ArrayList;

public class SearchByAge implements ISearchingStrategy {
    @Override
    public Iterable<Member> searchMembers(BoatClub boatClub,String input) {
        ArrayList<Member> members = new ArrayList<>();
        for(Member member : boatClub.getAllMembersLocally()){
             if(Integer.parseInt(member.getPersonalNumber().toString().substring(0,4))<Integer.parseInt(input))
                 members.add(member);
             return members;
        }
        return null;
    }
}
