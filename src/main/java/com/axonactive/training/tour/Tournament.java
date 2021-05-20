package com.axonactive.training.tour;

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

    List<Team> Teams;

    public void addTeam(Team newTeam) {
        if(!isValidTeam(newTeam)) 
        {
            throw new IllegalAccessError("Number of player must be [7;12]");
        }
        this.Teams.add(newTeam);
    }

    private boolean isValidTeam(Team team)
    {
        return team.getPlayers().size() > 6 && team.getPlayers().size() < 8;
    }

    public void generateScheduleMatch()
    {
        Map<String, List<Match>> tournament = SchedualUtil.generateTuornament(this.Teams);
        Set<String> keys = tournament.keySet();
        for (String key : keys) {
            System.out.println(key + " -> " + tournament.get(key));
        }
    }
}
