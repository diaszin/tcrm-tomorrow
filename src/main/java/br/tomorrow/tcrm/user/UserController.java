package br.tomorrow.tcrm.user;

import br.tomorrow.tcrm.user.dto.UserGetTokenDTO;
import br.tomorrow.tcrm.user.dto.UserLoginDTO;
import br.tomorrow.tcrm.user.dto.UserRemoveAccountDTO;
import br.tomorrow.tcrm.user.dto.UserSignupDTO;
import br.tomorrow.tcrm.user.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired()
    UserService userService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody() @Valid UserSignupDTO userSignupDTO) {
        this.userService.create(userSignupDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserGetTokenDTO login(@RequestBody() @Valid UserLoginDTO loginDTO) {
        return this.userService.login(loginDTO);
    }

    @DeleteMapping("/remove-account")
    public void removeAccount(@RequestBody() @Valid UserRemoveAccountDTO removeAccountDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = (UserEntity) authentication.getPrincipal();

        System.out.println(user.getId());
        this.userService.removeAccount(user.getId(), removeAccountDTO.password);
    }
}
