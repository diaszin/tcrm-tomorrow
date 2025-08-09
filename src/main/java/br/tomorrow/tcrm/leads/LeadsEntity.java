package br.tomorrow.tcrm.leads;

import br.tomorrow.tcrm.enterprise.EnterpriseEntity;
import br.tomorrow.tcrm.user.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Entity
@Table(name = "leads")
public class LeadsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @Email
    @NotBlank
    public String email;

    @NotNull
    @NotBlank
    public String name;

    @NotBlank
    public String cellNumber;

    @NotBlank
    public String description;

    @ManyToOne()
    @JoinColumn(name = "enteprise")
    public EnterpriseEntity enterprise;

    @ManyToOne()
    @JoinColumn(name =  "creator")
    @JsonIgnore
    public UserEntity creator;
}


