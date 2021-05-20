package com.axonactive.training;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import com.axonactive.training.company.Company;
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
        Teams.add(AxonTeam);
        Teams.add(FPTeam);
        Teams.add(BoschTeam);
        Teams.add(VNGTeam);
        Teams.add(TMATeam);
        Teams.add(CUSCTeam);
        if (Teams.size() % 2 != 0) {
            Teams.add(0, new Team("OFF",null));
        }

        Tournament tournament = new Tournament(Teams);

        tournament.generateScheduleMatch();

        // Map<String, List<Match>> tournament = SchedualUtil.generateTuornament(Teams);
        // Set<String> keys = tournament.keySet();
        // for (String key : keys) {
        //     System.out.println(key + tournament.get(key));
        //     // tournament.get(key)
        //     // .stream()
        //     // .forEach(match->System.out.println(match.toString()));
        // }

        // Map<String,Integer> schedule = new HashMap<>();
        // Set<String> keys = null;

        

        // while(true)
        // {
        //     System.out.println("1. Add Company");
        //     System.out.println("2. Add Team");
        //     System.out.println("3. Schedule match");
        //     System.out.println("4. Set score");
        //     System.out.println("0. Exit");
        //     System.out.println("Choose action");
        //     option = scanner.nextInt();
        //     switch (option) {
        //         case 1:
        //             System.out.println("Enter company name");
        //             String companyName = "";
        //             scanner.nextLine();
        //             companyName = scanner.nextLine();
        //             companies.add(new Company(companyName));
        //             break;
        //         case 2:
        //             int companyIndex = -1;
        //             System.out.println("Companies:");
        //             for (int index = 0 ; index < companies.size() ; index++){
        //                 System.out.println(index + ". " + companies.get(index).getName());
        //             }
        //             do{
        //                 System.out.println("Enter Company of Team");
        //                 companyIndex = scanner.nextInt();
        //             }while(companyIndex < 0 || companyIndex > companies.size());
        //             System.out.println("Enter Team name");
        //             String teamName = "";
        //             scanner.nextLine();
        //             teamName = scanner.nextLine();

        //             Team newTeam = new Team(teamName, companies.get(companyIndex).getId());
        //             Boolean enterPlayer = true;
        //             while(enterPlayer)
        //             {
        //                 try {
        //                     int chooseAction = -1;
        //                     System.out.println("0. Exit");
        //                     System.out.println("1. Add player");
        //                     System.out.println("Choose Action");
        //                     chooseAction = scanner.nextInt();
        //                     switch (chooseAction) {
        //                         case 1:
        //                             String fullName = "";
        //                             System.out.println("Enter Player name");
        //                             scanner.nextLine();
        //                             fullName = scanner.nextLine();                    
        //                             Emloyee player = new Emloyee(fullName, Gender.MALE ,companies.get(companyIndex).getId());
        //                             newTeam.addPlayer(player);
        //                             break;
        //                         case 0:
        //                             enterPlayer = false;
        //                             break;
        //                     }    
        //                 } catch (Exception e) {
                           
        //                     e.printStackTrace();
        //                     enterPlayer = false;
        //                 }
        //             }
        //             if(newTeam.getPlayers().size() > 2 && newTeam.getPlayers().size() < 13)
        //             {
        //                 Teams.add(newTeam);
        //             }else System.out.println("Player must be 7 - 12");
        //             break;
        //         case 3:
        //             System.out.println("Schedule match");
        //             System.out.println(Teams.size());
        //             int numberOfTeam = Teams.size();
        //             schedule.clear();
        //             for(int i = 1 ; i < numberOfTeam ; i++)
        //             {
        //                 for(int j = i+1 ; j <= numberOfTeam ; j ++)
        //                 {  
        //                     schedule.put( Teams.get(i-1).getName()+"-" + Teams.get(j-1).getName(),0); 
        //                 }
        //             }
        //             System.out.println("DONE");
        //             break;
        //         case 4:
        //             keys = schedule.keySet();
        //             List<String> keyIndex= new ArrayList<>();
        //             for (String key : keys) {                        
        //                 keyIndex.add(key);
        //                 System.out.println(keyIndex.indexOf(key) + " " + key + " " + schedule.get(key));
        //             }
        //             int indexOfKey = -1;
        //             int score = 0;
        //             System.out.println("Enter the math");
        //             indexOfKey = scanner.nextInt();
        //             System.out.println("Enter the score");
        //             score= scanner.nextInt();
        //             schedule.put(keyIndex.get(indexOfKey), score);
        //             break;
        //         case 5:
        //             keys = schedule.keySet();
                   
        //             for (String key : keys) {                        
        //                 System.out.println(key + " " + schedule.get(key));
        //             }
        //             break;
        //         case 0:
        //             for (Team team : Teams) {
        //                 System.out.println(team.getName());
        //                 for (Emloyee e : team.getPlayers()) {
        //                     System.out.println(e.getFullName());
        //                 }
        //             }
        //             return;
        //         default:
        //             break;
        //     }
        // }
       
    }
}