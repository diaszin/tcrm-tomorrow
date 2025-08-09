package br.tomorrow.tcrm.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @NotBlank
    private String name;

    @Email()
    @NotNull
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @NotNull
    private String password;

    private boolean enabled = true;
}
