package br.tomorrow.tcrm.exceptions;

public class UserTokenExpiredException extends RuntimeException {
    public UserTokenExpiredException(){super("Token expirado!");}
}
