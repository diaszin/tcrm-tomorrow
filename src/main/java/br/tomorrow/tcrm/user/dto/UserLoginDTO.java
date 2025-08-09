package br.tomorrow.tcrm.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UserLoginDTO {
    @Email
    @NotNull
    @NotBlank
    String email;

    @NotNull
    @NotBlank
    @Length(min = 6, message = "O tamanho da senha deve ser maior ou igual a 6" )
    String password;
}
