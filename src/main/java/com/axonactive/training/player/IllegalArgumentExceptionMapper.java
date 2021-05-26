package com.axonactive.training.player;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException>{

    @Override
    public Response toResponse(IllegalArgumentException exception) {
        return Response.status(Status.BAD_GATEWAY).entity(exception.getMessage()).build();
    }

   

        
}
