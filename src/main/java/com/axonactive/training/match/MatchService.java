package com.axonactive.training.match;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.axonactive.training.team.Team;

public class MatchService {
    @PersistenceContext
    private EntityManager matchEntity;

    public void add(Match newMatch) {
        validate(newMatch);
        this.matchEntity.persist(newMatch);
    }

    public void add(Team teamOne, Team teamTwo) {
        Match match = new Match();
        match.setTeamOne(teamOne);
        match.setTeamTwo(teamTwo);

        validate(match);
        this.matchEntity.persist(match);
    }

    public void update(Match newMatch) {
        validate(newMatch);
        if (newMatch.getId() == null) {
            throw new IllegalArgumentException("Match id is missing");
        }

        Match persistedMatch = this.matchEntity.find(Match.class, newMatch.getId());
        persistedMatch.updateMatch(newMatch);
        this.matchEntity.merge(persistedMatch);
    }

    public void delete(Long id) {
        this.matchEntity.remove(this.matchEntity.find(Match.class, id));
    }

    public Match find(Long id) {
        return this.matchEntity.find(Match.class, id);
    }

    public List<Match> findALl() {
        TypedQuery<Match> query = this.matchEntity.createNamedQuery("SELECT s FROM Match s", Match.class);
        return query.getResultList();
    }

    private void validate(Match match) {
        if (Objects.isNull(match)) {
            throw new IllegalArgumentException("Match is missing");
        }

        if (!match.isValid()) {
            throw new IllegalArgumentException("Match data is missing or invalid");
        }
    }
}
