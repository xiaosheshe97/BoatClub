import Controller.MainController;
import Model.BoatClub;
import Model.searchRule.ISearchingStrategy;
import Model.searchRule.SearchByAge;
import View.StartMenu;

public class Main {

    public static void main(String[] args) {

        ISearchingStrategy search = new SearchByAge();
        BoatClub boatClub = new BoatClub(search);
        StartMenu menu=new StartMenu();

        MainController user = new MainController(boatClub);

        user.memberAction(menu);

    }
}
