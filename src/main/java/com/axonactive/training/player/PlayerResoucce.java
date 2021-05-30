package com.axonactive.training.player;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("players")
@Stateless
public class PlayerResoucce extends WebApplicationException {

    @Inject
    PlayerService playerService;

    @Context
    UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Player newPlayer) {
        try {
            playerService.add(newPlayer);
        } catch (IllegalArgumentException e) {
            throw new PlayerException(e.getMessage(), Status.BAD_REQUEST);
        } catch (ConstraintViolationException eonstraintViolationException) {
            throw new PlayerException(eonstraintViolationException.getConstraintViolations().toString(),
                    Status.CONFLICT);
        }
        URI playerUri = uriInfo.getAbsolutePathBuilder().path(newPlayer.getId().toString()).build();
        return Response.created(playerUri).entity(playerUri.toString()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> findAll() {
        return playerService.findALl();
        // return Response.status(200)
        // .header("Access-Control-Allow-Origin", "*")
        // .header("Access-Control-Allow-Credentials", "true")
        // .header("Access-Control-Allow-Headers",
        // "origin, content-type, accept, authorization")
        // .header("Access-Control-Allow-Methods",
        // "GET, POST, PUT, DELETE, OPTIONS, HEAD")
        // .entity(playerService.findALl()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player findById(@PathParam("id") Long id) {
        Player player = this.playerService.find(id);

        if (player == null) {
            throw new PlayerException("Player does not exits", Status.BAD_REQUEST);
        }
        return player;
    }

    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        this.playerService.delete(id);
        return Response.ok().build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Player newPlayer) {
        this.playerService.update(id, newPlayer);
        return Response.ok().build();
    }

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> findByInsuranceNumber(@QueryParam("insuranceNumber") String insuranceNumber) {
        return playerService.findByInsuranceNumber(insuranceNumber);
    }

    @GET
    @Path("playfor")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> findByFirstName(@QueryParam("pfid") Long id) {
        System.out.println("Aass");
        return playerService.findAllPlayerPlayFor(id);
    }
}
