package com.axonactive.training.team;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.axonactive.training.player.Player;
import com.axonactive.training.player.PlayerService;

@Path("teams")
@Stateless
public class TeamResouce {
    @Inject
    TeamService teamService;

    @Inject
    PlayerService playerService;

    @Context
    UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Team newTeam) {
        teamService.add(newTeam);
        URI playerUri = uriInfo.getAbsolutePathBuilder().path(newTeam.getId().toString()).build();
        return Response.created(playerUri).entity(playerUri.toString()).build();
    }

    @POST
    @Path("{id}/players")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPlayer(@PathParam("id") Long id, Player newPlayer) {
        teamService.addPlayer(id, newPlayer);
        // URI playerUri =
        // uriInfo.getAbsolutePathBuilder().path(nenewPlayerwTeam.getId().toString()).build();
        return Response.ok().build();
    }

    @GET
    @Path("{id}/players")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> findAllPlayer(@PathParam("id") Long id) {
        return playerService.findAllPlayerPlayFor(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> findAll() {
        return teamService.findALl();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Team findById(@PathParam("id") Long id) {
        Team temp = teamService.find(id);
        // temp.getPlayers().stream().forEach(p -> {
        // System.out.println(p.getFullName());
        // });

        return temp;
    }
}
