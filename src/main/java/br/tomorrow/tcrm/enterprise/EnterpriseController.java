package br.tomorrow.tcrm.enterprise;

import br.tomorrow.tcrm.enterprise.dto.EnterpriseCreateDTO;
import br.tomorrow.tcrm.enterprise.dto.EnterpriseGetAllDTO;
import br.tomorrow.tcrm.enterprise.dto.EnterpriseUpdateDTO;
import br.tomorrow.tcrm.user.UserEntity;
import br.tomorrow.tcrm.user.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
    @Autowired
    EnterpriseService enterpriseService;

    @GetMapping
    public List<EnterpriseGetAllDTO> findAll() {
        return this.enterpriseService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody() @Valid() EnterpriseCreateDTO createDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        this.enterpriseService.create(user, createDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") @NotNull @NotBlank String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        this.enterpriseService.delete(id, user.getId());
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") @NotNull @NotBlank String id, @RequestBody() @Valid EnterpriseUpdateDTO updateDTO) {
        this.enterpriseService.update(id, updateDTO);
    }
}
