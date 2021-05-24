package com.axonactive.training.tour;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.axonactive.training.match.Match;
import com.axonactive.training.team.Team;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.EqualsAndHashCode.Exclude;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // mappedBy trỏ tới tên biến persons ở trong Address.
    @ManyToMany(mappedBy = "tours")
    // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới
    // query
    @EqualsAndHashCode.Exclude
    private Collection<Team> teams;

    @OneToMany
    @JoinColumn(name = "tour_id")
    private List<Match> Matchs = new LinkedList<>();

    // public void addTeam(Team newTeam) {
    // if (!isValidTeam(newTeam)) {
    // throw new IllegalAccessError("Number of player must be [7;12]");
    // }
    // this.Teams.add(newTeam);
    // }

    // public boolean isValidTeam(Team team) {
    // return team.getPlayers().size() > 6 && team.getPlayers().size() < 12;
    // }

    // public void generateScheduleMatch() {
    // LinkedList<Team> cloneTeams = new LinkedList<>(this.Teams);
    // // if (cloneTeams.size() % 2 != 0) {
    // // cloneTeams.addFirst(new Team("OFF",null));
    // // }
    // Map<String, List<Match>> tournament =
    // Schedule.generateTuornament(cloneTeams);
    // Set<String> keys = tournament.keySet();
    // for (String key : keys) {
    // System.out.println(key + " -> " + tournament.get(key));
    // }
    // }
}
