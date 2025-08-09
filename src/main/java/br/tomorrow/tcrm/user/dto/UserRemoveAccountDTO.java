package br.tomorrow.tcrm.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserRemoveAccountDTO {
    @NotNull
    @NotBlank
    public String password;
}
