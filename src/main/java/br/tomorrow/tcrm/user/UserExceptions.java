package br.tomorrow.tcrm.user;

import br.tomorrow.tcrm.user.exceptions.EmailIsRegisteredException;
import br.tomorrow.tcrm.user.exceptions.UserNotFoundException;
import br.tomorrow.tcrm.user.exceptions.UserPasswordNotMatchException;
import br.tomorrow.tcrm.user.exceptions.UserTokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import br.tomorrow.tcrm.exceptions.DefaultExceptionResponse;

@RestControllerAdvice
public class UserExceptions {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<DefaultExceptionResponse> userNotFoundException(UserNotFoundException exception) {
         DefaultExceptionResponse response = new DefaultExceptionResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EmailIsRegisteredException.class)
    public ResponseEntity<DefaultExceptionResponse> emailIsRegistered(EmailIsRegisteredException exception){
        DefaultExceptionResponse response = new DefaultExceptionResponse(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(UserPasswordNotMatchException.class)
    public ResponseEntity<DefaultExceptionResponse> passwordNotMatch(UserPasswordNotMatchException exception){
        DefaultExceptionResponse response = new DefaultExceptionResponse(exception.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

}
