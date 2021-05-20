package com.axonactive.training.team;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.axonactive.training.company.Company;
import com.axonactive.training.company.Gender;
import com.axonactive.training.company.Player;

import org.junit.jupiter.api.Test;

public class TeamTest {
    
    @Test
    public void whenPlayerWorkAtThatCompanyThenItReturnTrue(){
        Company testCompany = new Company("Test");
        Team team = new Team("Test Team", testCompany);
        Boolean result = team.isValidPlayer(new Player("Nhut Huy", Gender.MALE, testCompany));
        assertTrue(result);
    }

    @Test
    public void whenPlayerNotWorkAtThatCompanyThenItReturnFalse(){
        Company testCompany1 = new Company("Test");
        Company testCompany2 = new Company("Test");
        Team team = new Team("Test Team", testCompany1);
        Boolean result = team.isValidPlayer(new Player("Nhut Huy", Gender.MALE, testCompany2));
        assertFalse(result);
    }

    @Test
    public void whenNumberOfPlayerLessThanTeamSizeThenItReturnTrue()
    {
        Company testCompany1 = new Company("Test");
        Team team = new Team("Test Team", testCompany1);
        Player player1 = new Player("nhut huy",Gender.FEMALE,testCompany1);
        team.addPlayer(player1);
        Boolean result = team.isValidNumberOfPlayer();
        assertTrue(result);
    }
    @Test
    public void addPlayerwhenPlayerWorkAtThatCompanyThenItNotThing(){
        Company testCompany = new Company("Test");
        Team team = new Team("Test Team", testCompany);
        assertDoesNotThrow(()->team.addPlayer(new Player("Nhut Huy", Gender.MALE, testCompany)));
    }

    @Test
    public void addPlayerwhenPlayerNotWorkAtThatCompanyThenItThrowArithmeticException(){
        Company testCompany1 = new Company("Test");
        Company testCompany2 = new Company("Test");
        Team team = new Team("Test Team", testCompany1);
        assertThrows(ArithmeticException.class, ()->team.addPlayer(new Player("Nhut Huy", Gender.MALE, testCompany2)));
    }

    @Test
    public void addPlayerwhenNumberOfPlayerLessThanTeamSizeThenItReturnTrue()
    {
        Company testCompany1 = new Company("Test");
        Team team = new Team("Test Team", testCompany1);
        Player player1 = new Player("nhut huy",Gender.FEMALE,testCompany1);
        team.addPlayer(player1);
        team.addPlayer(player1);
        assertDoesNotThrow(()->team.addPlayer(new Player("Nhut Huy", Gender.MALE, testCompany1)));
    }

    @Test
    public void addPlayerwhenNumberOfPlayerMoreThanTeamSizeThenItReturnTrue()
    {
        Company testCompany1 = new Company("Test");
        Team team = new Team("Test Team", testCompany1);
        Player player1 = new Player("nhut huy",Gender.FEMALE,testCompany1);
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        players.add(player1);
        team.setPlayers(players);
        assertThrows(ArithmeticException.class, ()->team.addPlayer(new Player("Nhut Huy", Gender.MALE, testCompany1)));
    }

    @Test
    public void whenGiveTrueSizeOfTeamThenItReturnTrueSize(){
        Company testCompany1 = new Company("Test");
        Team team = new Team("Test Team", testCompany1);
        Player player1 = new Player("nhut huy",Gender.FEMALE,testCompany1);
        team.addPlayer(player1);
        team.addPlayer(player1);
        assertEquals(2, team.getSize());
    }

    @Test
    public void whenGiveNullSizeOfTeamThenItReturnIllegalAccessError(){
        Company testCompany1 = new Company("Test");
        Team team = new Team("Test Team", testCompany1);
        team.setPlayers(null);
        assertThrows(IllegalAccessError.class, ()->team.getSize());
    }
}
