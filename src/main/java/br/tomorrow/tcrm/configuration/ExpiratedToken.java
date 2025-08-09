package br.tomorrow.tcrm.configuration;

public class ExpiratedToken extends RuntimeException {
    public ExpiratedToken() {
        super("Token expirado! Logue novamente");
    }
}
