package com.axonactive.training.tour;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.axonactive.training.team.Team;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tournament {

    private List<Team> Teams = new LinkedList<>();

    public void addTeam(Team newTeam) {
        if(!isValidTeam(newTeam)) 
        {
            throw new IllegalAccessError("Number of player must be [7;12]");
        }
        this.Teams.add(newTeam);
    }

    public boolean isValidTeam(Team team)
    {
        return team.getPlayers().size() > 6 && team.getPlayers().size() < 12;
    }

    public void generateScheduleMatch()
    {
        LinkedList<Team> cloneTeams = new LinkedList<>(this.Teams);
        // if (cloneTeams.size() % 2 != 0) {
        //     cloneTeams.addFirst(new Team("OFF",null));
        // }
        Map<String, List<Match>> tournament = Schedule.generateTuornament(cloneTeams);
        Set<String> keys = tournament.keySet();
        for (String key : keys) {
            System.out.println(key + " -> " + tournament.get(key));
        }
    }
}
