package com.axonactive.training.team;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.axonactive.training.player.Player;

public class TeamService {
    @PersistenceContext
    private EntityManager teamEntity;

    public void add(Team newTeam) {
        validate(newTeam);
        this.teamEntity.persist(newTeam);
    }

    public void update(Team newTeam) {
        validate(newTeam);
        if (newTeam.getId() == null) {
            throw new IllegalArgumentException("Team id is missing");
        }

        Team persistedTeam = this.teamEntity.find(Team.class, newTeam.getId());
        persistedTeam.updateTeam(newTeam);
        this.teamEntity.merge(persistedTeam);
    }

    public void delete(Long id) {
        this.teamEntity.remove(this.teamEntity.find(Team.class, id));
    }

    public Team find(Long id) {
        return this.teamEntity.find(Team.class, id);
    }

    public List<Team> findALl() {
        TypedQuery<Team> query = this.teamEntity.createNamedQuery(Team.GET_ALL_QUERY, Team.class);
        return query.getResultList();
    }

    public List<Team> findByInsuranceNumber(String teamName) {
        TypedQuery<Team> query = this.teamEntity.createNamedQuery(Team.GET_BY_NAME, Team.class);
        query.setParameter("teamName", teamName);
        return query.getResultList();
    }

    public void addPlayer(Long id, Player newPlayer) {
        Team team = this.teamEntity.find(Team.class, id);
        if (team == null) {
            throw new IllegalArgumentException("Team is not exited");
        }
        team.addPlayer(newPlayer);
        this.teamEntity.merge(team);
    }

    private void validate(Team team) {
        if (Objects.isNull(team)) {
            throw new IllegalArgumentException("Team is missing");
        }

        if (!team.isValid()) {
            throw new IllegalArgumentException("Team data is missing or invalid");
        }
    }

}
