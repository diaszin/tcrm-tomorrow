package br.tomorrow.tcrm.enterprise;

import br.tomorrow.tcrm.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table( name = "empresas")
public class EnterpriseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @NotNull
    @NotBlank
    public String name;

    @NotBlank
    public String description;

    @ManyToOne( fetch = FetchType.EAGER )
    @JoinColumn(name = "creator_id")
    @JsonIgnore
    public UserEntity creator;
}
