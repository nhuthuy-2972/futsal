package com.axonactive.training.match;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.axonactive.training.team.Team;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team_one_id")
    @NotNull
    private Team teamOne;

    @ManyToOne
    @JoinColumn(name = "team_two_id")
    @NotNull
    private Team teamTwo;

    @Column(name = "team_one_score")
    private int teamOneScore = 0;

    @Column(name = "team_two_score")
    private int teamTwoScore = 0;

    public boolean isValid() {
        return Objects.nonNull(this.teamOne) && Objects.nonNull(this.teamTwo);
    }

    public void updateMatch(Match newMatch) {
        this.teamOne = newMatch.teamOne;
        this.teamTwo = newMatch.teamTwo;
        this.teamOneScore = newMatch.teamOneScore;
        this.teamTwoScore = newMatch.teamTwoScore;
    }
}
