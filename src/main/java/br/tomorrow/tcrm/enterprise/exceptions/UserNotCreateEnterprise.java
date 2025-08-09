package br.tomorrow.tcrm.enterprise.exceptions;

public class UserNotCreateEnterprise extends RuntimeException {
    public UserNotCreateEnterprise() {
        super("Usuário não tem permissão para deletar a empresa");
    }
}
