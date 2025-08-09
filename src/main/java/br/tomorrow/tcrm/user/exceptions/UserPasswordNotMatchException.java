package br.tomorrow.tcrm.user.exceptions;

public class UserPasswordNotMatchException extends RuntimeException {
    public UserPasswordNotMatchException() {
        super("Senha incorreta !");
    }
}
