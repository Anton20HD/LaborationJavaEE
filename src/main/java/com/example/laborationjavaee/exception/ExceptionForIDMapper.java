package com.example.laborationjavaee.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionForIDMapper implements ExceptionMapper<ExceptionForID> {

    @Override
    public Response toResponse(ExceptionForID e) {
        return Response.status(404).entity(e.getMessage()).build();
    }
}
