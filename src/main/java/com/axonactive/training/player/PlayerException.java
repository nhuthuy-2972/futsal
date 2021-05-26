package com.axonactive.training.player;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class PlayerException extends WebApplicationException{
    public PlayerException(String message, Status status){
        super(Response.status(status).entity(message).build());
    }
}
