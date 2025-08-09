package br.tomorrow.tcrm.leads;

import br.tomorrow.tcrm.leads.dto.LeadsCreateDTO;
import br.tomorrow.tcrm.user.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leads")
public class LeadsController {

    @Autowired
    LeadsService leadsService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody LeadsCreateDTO createDTO){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user =  (UserEntity) authentication.getPrincipal();

        this.leadsService.create(user,  createDTO);
    }

    @GetMapping()
    public List<LeadsEntity> getAll(){
        return this.leadsService.getAll();
    }

    @GetMapping("/user")
    public List<LeadsEntity> getMyLeads(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        return this.leadsService.getAllByUserID(user.getId());
    }


}
