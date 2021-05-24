package com.axonactive.training.team;

import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TeamService {
        @PersistenceContext
        private EntityManager teamEntity;

        public void add(Team newTeam){
            validate(newTeam);
            this.teamEntity.persist(newTeam);
        }

        // public void addPlayer(Player newPlayer){
        //     if(!isValidNumberOfPlayer())
        //     {
        //         throw new ArithmeticException("Maxinum of players");
        //     }
        //     else if(!isValidPlayer(newPlayer))
        //     {
        //         throw new ArithmeticException("Player must be working for that company");
        //     }
        //     this.players.add(newPlayer);
        // }


        private void validate(Team team) {
            if (Objects.isNull(team)){
                throw new IllegalArgumentException("Team is missing");
            }
    
            if (!team.isValid()) {
                throw new IllegalArgumentException("Team data is missing or invalid");
            }
        }
    
}
