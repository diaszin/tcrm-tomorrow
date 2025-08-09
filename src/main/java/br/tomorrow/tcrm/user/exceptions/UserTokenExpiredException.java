package br.tomorrow.tcrm.user.exceptions;

public class UserTokenExpiredException extends RuntimeException {
    public UserTokenExpiredException(){super("Token expirado!");}
}
