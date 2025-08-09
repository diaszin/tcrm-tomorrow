package br.tomorrow.tcrm.enterprise.exceptions;

public class EnterpriseNotFoundException extends RuntimeException {
    public EnterpriseNotFoundException() {
        super("A empresa não foi encontrada");
    }
}
