package com.axonactive.training.team;

import java.util.ArrayList;
import java.util.List;

import com.axonactive.training.company.Company;
import com.axonactive.training.company.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    private final int MAX_TEAM_SIZE = 4;

    private static Long autoId = 100000000L;

    private Long id;

    private String name;

    private Company representOfCompany;

    private List<Player> players = new ArrayList<>();


    public Team(String name, Company representOfCompany) {
        this.id = autoId++;
        this.name = name;
        this.representOfCompany = representOfCompany;
    }
    
    public void addPlayer(Player newPlayer){
        if(!isValidNumberOfPlayer())
        {
            throw new ArithmeticException("Maxinum of players");
        }
        else if(!isValidPlayer(newPlayer))
        {
            throw new ArithmeticException("Player must be working for that company");
        }
        this.players.add(newPlayer);
    }

    private Boolean isValidPlayer(Player emloyee){
        return emloyee.getWorkAt().getId() == this.representOfCompany.getId();
    }

    private Boolean isValidNumberOfPlayer(){
        return this.players.size() < this.MAX_TEAM_SIZE;
    }

    public int getSize() {
        return this.players.size();
    }
}
