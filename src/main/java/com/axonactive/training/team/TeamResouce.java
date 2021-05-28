package com.axonactive.training.team;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("teams")
@Stateless
public class TeamResouce {
    @Inject
    TeamService teamService;

    @Context
    UriInfo uriInfo;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Team newTeam){
        System.out.println(newTeam.getName() + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        teamService.add(newTeam);

        // try {
        //     teamService.add(newTeam);
        // } catch (IllegalArgumentException  e) {
        //     throw new PlayerException(e.getMessage(), Status.BAD_REQUEST);
        // }catch(ConstraintViolationException eonstraintViolationException){
        //     throw new PlayerException(eonstraintViolationException.getConstraintViolations().toString(), Status.CONFLICT);
        // }
        URI playerUri = uriInfo.getAbsolutePathBuilder().path(newTeam.getId().toString()).build();
        return Response.created(playerUri).entity(playerUri.toString()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> findAll(){
        return teamService.findALl();
    }
}
