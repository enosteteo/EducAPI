package br.ufpb.dcx.apps4society.educapi.services.exceptions;

public class InvalidUserException extends IllegalArgumentException{

    public InvalidUserException() {
        super();
    }

    public InvalidUserException(String s) {
        super(s);
    }
}
