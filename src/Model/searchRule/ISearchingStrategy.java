package Model.searchRule;

import Model.BoatClub;
import Model.Member;

public interface ISearchingStrategy {
    public Iterable<Member> searchMembers(BoatClub boatClub, String input);
}
