package br.com.invistaix.InvistaIX.exception;

public class UnauthorizedAccessException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 7316705139333178672L;

    public UnauthorizedAccessException(){

    };

    public UnauthorizedAccessException(String message) {
        super(message);
    }

}