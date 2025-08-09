package br.tomorrow.tcrm.exceptions;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class InvalidFields {
    public String field;
    public String description;
}
