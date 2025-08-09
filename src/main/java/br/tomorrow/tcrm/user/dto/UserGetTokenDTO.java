package br.tomorrow.tcrm.user.dto;

import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class UserGetTokenDTO {
    public String accessToken;
    public Optional<String> refreshToken;
}
