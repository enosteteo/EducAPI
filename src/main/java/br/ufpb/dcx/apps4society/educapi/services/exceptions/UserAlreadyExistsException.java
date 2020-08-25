package br.ufpb.dcx.apps4society.educapi.services.exceptions;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
