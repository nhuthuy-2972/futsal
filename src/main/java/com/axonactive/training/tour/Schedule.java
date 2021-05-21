package com.axonactive.training.tour;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import com.axonactive.training.team.Team;

public class Schedule{
    public static void rotateTeamList(LinkedList<Team> a) {
        Team firstElement = a.pollFirst();
        Team lastElememt = a.pollLast();
        a.addFirst(lastElememt);
        a.addFirst(firstElement);
    }

    public static List<Match> matchParticipants(List<Team> participants) {
        LinkedList<Team> p = new LinkedList<>((LinkedList<Team>) participants);
        List<Match> pairings = new LinkedList<>();
        while (p.size() != 0) {
            Team participantA = p.pollFirst();
            Team participantB = p.pollLast();
            // if (participantA != null && participantB != null) {
                Match match = new Match(participantA,participantB);
                pairings.add(match);
            // }
        }
        return pairings;
    }

    public static Map<String, List<Match>> generateTuornament(List<Team> teams) {
        Map<String, List<Match>> tournamentRounds = new HashMap<>();
        LinkedList<Team> p = new LinkedList<>(teams);
        if(p.size() % 2 != 0)
        {
            p.addFirst(new Team("OFF",null));
        }
        int rounds = p.size() - 1;
        
        for (int i = 0; i < rounds; i++) {
            tournamentRounds.put("Round" + (i + 1), matchParticipants(p));
            rotateTeamList(p);
        }
        return tournamentRounds;
    }
}