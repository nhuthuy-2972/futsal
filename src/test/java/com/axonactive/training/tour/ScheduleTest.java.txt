package com.axonactive.training.tour;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.axonactive.training.company.Company;
import com.axonactive.training.team.Team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScheduleTest {

    private List<Company> companies;
    private List<Team> Teams;
    @BeforeEach
    public void init(){
        this.companies = new ArrayList<>();
        companies.add(new Company("Axon Active"));
        companies.add(new Company("FPT"));
        companies.add(new Company("BOSCH"));
        companies.add(new Company("VNG"));
       
        this.Teams = new LinkedList<>();

        Team AxonTeam = new Team("Axon-Active", companies.get(0));
        Team FPTeam = new Team("FPT-CT", companies.get(1));
        Team BoschTeam = new Team("Bosch", companies.get(2));
        Team VNGTeam = new Team("VNG-FC", companies.get(3));
       
        System.out.println(AxonTeam.getSize());
        Teams.add(AxonTeam);
        Teams.add(FPTeam);
        Teams.add(BoschTeam);
        Teams.add(VNGTeam);
    }

    @Test
    public void generateScheduleEvenMatch(){

        Map<String, List<Match>> tournamentRounds = new HashMap<>();
        LinkedList<Match> round1 = new LinkedList<>();
        round1.add(new Match(this.Teams.get(0),this.Teams.get(3)));
        round1.add(new Match(this.Teams.get(1),this.Teams.get(2)));

        LinkedList<Match> round2 = new LinkedList<>();
        round2.add(new Match(this.Teams.get(0),this.Teams.get(2)));
        round2.add(new Match(this.Teams.get(3),this.Teams.get(1)));

        LinkedList<Match> round3 = new LinkedList<>();
        round3.add(new Match(this.Teams.get(0),this.Teams.get(1)));
        round3.add(new Match(this.Teams.get(2),this.Teams.get(3)));

        tournamentRounds.put("Round1", round1);
        tournamentRounds.put("Round2", round2);
        tournamentRounds.put("Round3", round3);
        
        assertEquals(tournamentRounds, Schedule.generateTuornament(this.Teams));
    }

    @Test
    public void generateScheduleOddMatch(){
        this.Teams.remove(this.Teams.size() -1);
        Map<String, List<Match>> tournamentRounds = new HashMap<>();
        LinkedList<Match> round1 = new LinkedList<>();
        round1.add(new Match(new Team("OFF",null) , this.Teams.get(2)));
        round1.add(new Match(this.Teams.get(0),this.Teams.get(1)));

        LinkedList<Match> round2 = new LinkedList<>();
        round2.add(new Match(new Team("OFF",null),this.Teams.get(1)));
        round2.add(new Match(this.Teams.get(2),this.Teams.get(0)));

        LinkedList<Match> round3 = new LinkedList<>();
        round3.add(new Match(new Team("OFF",null),this.Teams.get(0)));
        round3.add(new Match(this.Teams.get(1),this.Teams.get(2)));

        tournamentRounds.put("Round1", round1);
        tournamentRounds.put("Round2", round2);
        tournamentRounds.put("Round3", round3);
      
        assertEquals(tournamentRounds.toString(), Schedule.generateTuornament(this.Teams).toString());
    }
}
