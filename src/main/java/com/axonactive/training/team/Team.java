package com.axonactive.training.team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.axonactive.training.player.Player;
import com.axonactive.training.tour.Tournament;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
@Table(name = "tbl_team")
@NamedQueries({ @NamedQuery(name = Team.GET_ALL_QUERY, query = "SELECT s FROM Team s"),
        @NamedQuery(name = Team.GET_BY_NAME, query = "SELECT s FROM Team s WHERE s.name =:teamName") })
public class Team {

    public static final String QUALIFIER = "com.axonactive.training.team.";

    public static final String GET_ALL_QUERY = QUALIFIER + "getAll";

    public static final String GET_BY_NAME = QUALIFIER + "getByTeamName";

    private final int MAX_TEAM_SIZE = 13;

    private final int MIN_TEAM_SIZE = 5;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    @Size(max = 50)
    private String name;

    @OneToMany
    @JoinColumn(name = "team_id")
    private List<Player> players = new ArrayList<>();

    @JoinTable(name = "team_tour", // Tạo ra một join Table tên là "address_person"
            joinColumns = @JoinColumn(name = "team_id"), // TRong đó, khóa ngoại chính là address_id trỏ tới class
                                                         // hiện tại (Address)
            inverseJoinColumns = @JoinColumn(name = "tour_id") // Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (Person)
    )
    private Collection<Tournament> tours;

    public Boolean isValidNumberOfPlayer() {
        int teamSize = this.players.size();
        return teamSize < this.MAX_TEAM_SIZE && teamSize > this.MIN_TEAM_SIZE;
    }

    public int getSize() {
        if (this.players == null) {
            throw new IllegalAccessError("The players is null");
        }
        return this.players.size();
    }

    public boolean isValid() {
        return Objects.nonNull(this.name) && this.isValidNumberOfPlayer();
    }

    public boolean isNumberOfTeamLessThanMaxTeamSize() {
        return this.players.size() < this.MAX_TEAM_SIZE;
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
