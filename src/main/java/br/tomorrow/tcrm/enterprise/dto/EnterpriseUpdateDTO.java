package br.tomorrow.tcrm.enterprise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class EnterpriseUpdateDTO {
    @NotBlank
    @NotNull
    public String name;

    @NotBlank
    @NotNull
    public String description;
}
