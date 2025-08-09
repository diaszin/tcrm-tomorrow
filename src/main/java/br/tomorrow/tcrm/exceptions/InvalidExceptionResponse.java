package br.tomorrow.tcrm.exceptions;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
public class InvalidExceptionResponse extends DefaultExceptionResponse{
    public String message = "Requisição inválida. Por favor, verifique os dados enviados";
    public List<InvalidFields> fields;

    public InvalidExceptionResponse(List<InvalidFields> fields){
        this.fields = fields;
    }
}
