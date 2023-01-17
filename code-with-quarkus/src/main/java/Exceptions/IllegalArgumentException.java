package Exceptions;

import javax.ws.rs.WebApplicationException;

public class IllegalArgumentException extends WebApplicationException {
    public IllegalArgumentException(String message) {
        super("IllegalArgumentException error: " + message);
    }
    public IllegalArgumentException() {
        super();
    }
}

