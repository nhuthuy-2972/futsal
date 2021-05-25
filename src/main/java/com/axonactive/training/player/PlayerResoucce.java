package com.axonactive.training.player;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

@Path("players")
@Stateless
public class PlayerResoucce extends WebApplicationException{
    @Inject
    private PlayerService playerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addPlayer(Player newPlayer){
        playerService.add(newPlayer);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> allPlayer(){
        return playerService.findALl();
    }
}
