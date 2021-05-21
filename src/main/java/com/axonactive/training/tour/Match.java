package com.axonactive.training.tour;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.axonactive.training.team.Team;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Match {
    
    private Team teamOne;

    private Team teamTwo;

    @Override
    public String toString() {
        return "{ "+ getTeamOne().getName() + " - "
             + getTeamTwo().getName() +
            "}";
    }
}
