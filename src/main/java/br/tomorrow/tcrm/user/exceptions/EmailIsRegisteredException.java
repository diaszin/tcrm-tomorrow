package br.tomorrow.tcrm.user.exceptions;

public class EmailIsRegisteredException extends RuntimeException {
    public EmailIsRegisteredException() {
        super("Email já registrado !");
    }

}
