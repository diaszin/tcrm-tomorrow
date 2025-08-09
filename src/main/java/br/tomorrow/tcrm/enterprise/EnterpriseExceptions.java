package br.tomorrow.tcrm.enterprise;

import br.tomorrow.tcrm.enterprise.exceptions.EnterpriseIDNotValid;
import br.tomorrow.tcrm.enterprise.exceptions.EnterpriseNotFoundException;
import br.tomorrow.tcrm.enterprise.exceptions.UserNotCreateEnterprise;
import br.tomorrow.tcrm.exceptions.DefaultExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EnterpriseExceptions {
    @ExceptionHandler(EnterpriseNotFoundException.class)
    public ResponseEntity<DefaultExceptionResponse> enterpriseNotFound(EnterpriseNotFoundException exception){
        DefaultExceptionResponse response = new DefaultExceptionResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


    @ExceptionHandler(EnterpriseIDNotValid.class)
    public ResponseEntity<DefaultExceptionResponse> enterpriseIdNotValid(EnterpriseIDNotValid exception){
        DefaultExceptionResponse response = new DefaultExceptionResponse(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(UserNotCreateEnterprise.class)
    public ResponseEntity<DefaultExceptionResponse> userNotAllowedToDeleteEnterprise(UserNotCreateEnterprise exception){
        DefaultExceptionResponse response = new DefaultExceptionResponse(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
