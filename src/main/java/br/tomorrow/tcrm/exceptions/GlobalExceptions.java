package br.tomorrow.tcrm.exceptions;

import br.tomorrow.tcrm.configuration.ExpiratedToken;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;


@RestControllerAdvice
public class GlobalExceptions {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<InvalidExceptionResponse> methordArgumentException(MethodArgumentNotValidException exception) {


        List<InvalidFields> errors = exception.getBindingResult().getFieldErrors().parallelStream().map(error -> new InvalidFields(error.getField(), error.getDefaultMessage())).toList();

        InvalidExceptionResponse response = new InvalidExceptionResponse(errors);

        return ResponseEntity.badRequest().body(response);

    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<InvalidExceptionResponse> handlerMethodValidationException(HandlerMethodValidationException exception) {


        List<InvalidFields> errors = exception.getAllErrors().parallelStream().map((error) -> {
            FieldError fieldError = (FieldError) error;
            return new InvalidFields(fieldError.getField(), fieldError.getDefaultMessage());
        }).toList();

        InvalidExceptionResponse response = new InvalidExceptionResponse(errors);

        return ResponseEntity.badRequest().body(response);

    }

    @ExceptionHandler(ExpiratedToken.class)
    public ResponseEntity<DefaultExceptionResponse> userTokenExpired(ExpiratedToken exception){
        UserTokenExpiredException tokenExpiredException = new UserTokenExpiredException();
        DefaultExceptionResponse response = new DefaultExceptionResponse(tokenExpiredException.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
