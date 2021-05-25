package com.axonactive.training.player;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("players")
@Stateless
public class PlayerResoucce extends WebApplicationException{
    
    @Context
    private UriInfo uriInfo;

    @Inject
    private PlayerService playerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlayer(Player newPlayer){
        playerService.add(newPlayer);
        URI playerUri = uriInfo.getAbsolutePathBuilder().path(newPlayer.getId().toString()).build();
        return Response.created(playerUri).entity(playerUri.toString()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getAllPlayer(){
        return playerService.findALl();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player getPlayerById( @PathParam("id") Long id){
      
        return playerService.find(id);
    }

    @DELETE
    @Path("{id}")
    public Response deletePlayerById(@PathParam("id") Long id) {
        this.playerService.delete(id);
        return Response.ok().build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updatePlayer(@PathParam("id") Long id ,Player newPlayer) {
        this.playerService.update(id,newPlayer);
        return Response.ok().build();
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getPlayerByDOB( @QueryParam("insuranceNumber") String insuranceNumber){
        return playerService.findByInsuranceNumber(insuranceNumber);
    }
}
