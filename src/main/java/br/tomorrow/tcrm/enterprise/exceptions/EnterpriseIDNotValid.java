package br.tomorrow.tcrm.enterprise.exceptions;

public class EnterpriseIDNotValid extends RuntimeException {
    public EnterpriseIDNotValid() {
        super("O ID enviado não é valido");
    }
}
