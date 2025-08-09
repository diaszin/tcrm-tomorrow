package br.tomorrow.tcrm.user;

import br.tomorrow.tcrm.configuration.JwtUtil;
import br.tomorrow.tcrm.user.dto.UserGetTokenDTO;
import br.tomorrow.tcrm.user.dto.UserLoginDTO;
import br.tomorrow.tcrm.user.dto.UserSignupDTO;
import br.tomorrow.tcrm.user.exceptions.EmailIsRegisteredException;
import br.tomorrow.tcrm.user.exceptions.UserNotFoundException;
import br.tomorrow.tcrm.user.exceptions.UserPasswordNotMatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.UUID;


@Service
public class UserService {
    @Autowired()
    UserRepository userRepository;

    @Autowired()
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtil jwtUtil;


    public void create(UserSignupDTO user) {
        try {
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);

            this.userRepository.save(UserSignupDTO.toEntity(user));
        } catch (DataIntegrityViolationException e) {
            throw new EmailIsRegisteredException();
        }
    }

    public UserGetTokenDTO login(UserLoginDTO userLoginDTO) {
        UserEntity user = this.userRepository.findByEmail(userLoginDTO.getEmail()).orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            throw new UserNotFoundException();
        }


        String token = jwtUtil.generateToken(user.getId());
        return new UserGetTokenDTO(token, null);
    }

    public UserEntity findById(UUID id) {
        return this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public void removeAccount(UUID id, String password) {
        UserEntity user = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new UserPasswordNotMatchException();
        }

        this.userRepository.deleteById(id);

    }
}
