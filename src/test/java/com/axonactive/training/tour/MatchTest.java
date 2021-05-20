package com.axonactive.training.tour;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.axonactive.training.team.Team;

import org.junit.jupiter.api.Test;

public class MatchTest {
    @Test
    public void toStringTest(){
        Team team1 = new Team("Team 1",null);
        Team team2 = new Team("Team 2",null);
        Match match = new Match(team1,team2);
        String result = "{ Team 1 - Team 2}";
        assertEquals(result, match.toString());
    }
}
