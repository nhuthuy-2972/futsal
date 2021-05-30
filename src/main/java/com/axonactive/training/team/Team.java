package com.axonactive.training.team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.axonactive.training.player.Player;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tbl_team")
@NamedQueries({ @NamedQuery(name = Team.GET_ALL_QUERY, query = "SELECT t FROM Team t"),
        @NamedQuery(name = Team.GET_BY_NAME, query = "SELECT t FROM Team t WHERE t.name =:teamName") })
public class Team {

    public static final String QUALIFIER = "com.axonactive.training.team.";

    public static final String GET_ALL_QUERY = QUALIFIER + "getAll";

    public static final String GET_BY_NAME = QUALIFIER + "getByTeamName";

    private static final int MAX_TEAM_SIZE = 13;

    private static final int MIN_TEAM_SIZE = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(max = 50)
    private String name;

    @OneToMany(mappedBy = "playFor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    @JsonbTransient
    private List<Player> players = new ArrayList<>();

    public Boolean isValidNumberOfPlayer() {
        int teamSize = this.players.size();
        return teamSize < Team.MAX_TEAM_SIZE && teamSize > Team.MIN_TEAM_SIZE;
    }

    public int getSize() {
        if (this.players == null) {
            throw new IllegalAccessError("The players is null");
        }
        return this.players.size();
    }

    public boolean isValid() {
        return Objects.nonNull(this.name);
    }

    public boolean isNumberOfTeamLessThanMaxTeamSize() {
        return this.players.size() < Team.MAX_TEAM_SIZE;
    }

    public void updateTeam(Team newTeam) {
        this.name = newTeam.name;
        this.players = newTeam.players;
    }

    public void addPlayer(Player newPlayer) {
        if (!this.isNumberOfTeamLessThanMaxTeamSize()) {
            throw new ArithmeticException("Maxinum of players");
        }
        this.players.add(newPlayer);
    }
}
