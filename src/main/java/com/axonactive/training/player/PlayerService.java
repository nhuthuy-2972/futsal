package com.axonactive.training.player;

import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


public class PlayerService {
    @PersistenceContext
    private EntityManager playerEntity;

    public void add(Player newPlayer){
        validate(newPlayer);
        this.playerEntity.persist(newPlayer);
    }

    public void update(Long id ,Player newPlayer){
        validate(newPlayer);
        Player persistedPlayer = this.playerEntity.find(Player.class, id);
        if (Objects.isNull(persistedPlayer)){
            throw new IllegalArgumentException("Player is not exits");
        }
        persistedPlayer.updatePlayer(newPlayer);
        this.playerEntity.merge(persistedPlayer);
    }


    public void delete(Long id){
        this.playerEntity.remove(this.playerEntity.find(Player.class, id));
    }

    public Player find(Long id){
        return this.playerEntity.find(Player.class, id);
    }

    public List<Player> findALl(){
        TypedQuery<Player> query = this.playerEntity.createNamedQuery(Player.GET_ALL_QUERY,Player.class);
        return query.getResultList();
    }

    public  List<Player> findByInsuranceNumber(String insuranceNumber) {
        TypedQuery<Player> query = this.playerEntity.createNamedQuery(Player.GET_BY_INSURANCE_NUMBER, Player.class);
        query.setParameter("playerInsuranceNumber", insuranceNumber);
        return query.getResultList();
    }

    private void validate(Player player)
    {
        if (Objects.isNull(player)){
            throw new IllegalArgumentException("Player is missing");
        }

        if (!player.isValid()) {
            throw new IllegalArgumentException("Player data is missing or invalid");
        }
    }
}
