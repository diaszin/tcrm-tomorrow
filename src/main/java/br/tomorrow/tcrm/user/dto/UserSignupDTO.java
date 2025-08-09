package br.tomorrow.tcrm.user.dto;

import br.tomorrow.tcrm.user.UserEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserSignupDTO {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @NotNull
    @Length(min = 6, message = "O tamanho da senha deve ser maior ou igual a 6" )
    private String password;

    public static UserEntity toEntity(UserSignupDTO dto){
        UserEntity entity = new UserEntity();

        entity.setEmail(dto.email);
        entity.setPassword(dto.password);
        entity.setName(dto.name);

        return entity;
    }
}
