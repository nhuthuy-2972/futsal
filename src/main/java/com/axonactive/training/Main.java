package com.axonactive.training;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import com.axonactive.training.company.Company;
import com.axonactive.training.company.Gender;
import com.axonactive.training.company.Player;
import com.axonactive.training.team.Team;
import com.axonactive.training.tour.Tournament;


class Main{

        public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option;
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("Axon Active"));
        companies.add(new Company("FPT"));
        companies.add(new Company("BOSCH"));
        companies.add(new Company("VNG"));
        companies.add(new Company("TMA"));
        companies.add(new Company("CUSC"));
        List<Team> Teams = new LinkedList<>();

        Team AxonTeam = new Team("Axon-Active", companies.get(0));
        Team FPTeam = new Team("FPT-CT", companies.get(1));
        Team BoschTeam = new Team("Bosch", companies.get(2));
        Team VNGTeam = new Team("VNG-FC", companies.get(3));
        Team TMATeam = new Team("TMA-HCM", companies.get(4));
        Team CUSCTeam = new Team("CUSC-FC", companies.get(5));
        System.out.println(AxonTeam.getSize());
        Teams.add(AxonTeam);
        Teams.add(FPTeam);
        Teams.add(BoschTeam);
        Teams.add(VNGTeam);
        Teams.add(TMATeam);
        Teams.add(CUSCTeam);
        
        

        Tournament tournament = new Tournament(Teams);
        // tournament.addTeam(AxonTeam);
        // tournament.generateScheduleMatch();
   
        while(true){
            System.out.println("1. Add Company");
            System.out.println("2. Add Team");
            System.out.println("3. Schedule match");
            System.out.println("0. Exit");
            System.out.println("Choose action");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Enter company name");
                    String companyName = "";
                    scanner.nextLine();
                    companyName = scanner.nextLine();
                    companies.add(new Company(companyName));
                    break;
                case 2:
                    int companyIndex = -1;
                    System.out.println("Companies:");
                    for (int index = 0 ; index < companies.size() ; index++){
                        System.out.println(index + ". " + companies.get(index).getName());
                    }
                    do{
                        System.out.println("Enter Company of Team");
                        companyIndex = scanner.nextInt();
                    }while(companyIndex < 0 || companyIndex > companies.size());
                    System.out.println("Enter Team name");
                    String teamName = "";
                    scanner.nextLine();
                    teamName = scanner.nextLine();

                    Team newTeam = new Team(teamName, companies.get(companyIndex));
                    Boolean enterPlayer = true;
                    while(enterPlayer)
                    {
                        try {
                            int chooseAction = -1;
                            System.out.println("0. Exit");
                            System.out.println("1. Add player");
                            System.out.println("Choose Action");
                            chooseAction = scanner.nextInt();
                            switch (chooseAction) {
                                case 1:
                                    String fullName = "";
                                    System.out.println("Enter Player name");
                                    scanner.nextLine();
                                    fullName = scanner.nextLine();                    
                                    Player player = new Player(fullName, Gender.MALE ,companies.get(companyIndex));
                                    newTeam.addPlayer(player);
                                    break;
                                case 0:
                                    enterPlayer = false;
                                    break;
                            }    
                        } catch (Exception e) {
                           
                            e.printStackTrace();
                            enterPlayer = false;
                        }
                    }
                    try {
                        tournament.addTeam(newTeam);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("Schedule match");
                    tournament.generateScheduleMatch();
                    break;
                case 0:
                    return;
                default:
                    break;
            }
        }
       
    }
}