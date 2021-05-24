package com.axonactive.training.team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
public class Team {

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
    @JoinColumn(name = "player_id")
    private List<Player> players = new ArrayList<>();
    
    public Boolean isValidNumberOfPlayer(){
        int teamSize = this.players.size();
        return teamSize < this.MAX_TEAM_SIZE && teamSize > this.MIN_TEAM_SIZE;
    }

    public int getSize() {
        if(this.players == null)
        {
            throw new IllegalAccessError("The players is null");
        }
        return this.players.size();
    }

    
    public boolean isValid() {
        return Objects.nonNull(this.name)
        &&this.isValidNumberOfPlayer();
	}
}
