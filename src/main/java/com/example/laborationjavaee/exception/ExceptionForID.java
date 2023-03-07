package com.example.laborationjavaee.exception;

import jakarta.ws.rs.WebApplicationException;

public class ExceptionForID extends WebApplicationException {

    public ExceptionForID() {
            super();
        }

        public ExceptionForID(String message) {
                super(message);


        }

}
