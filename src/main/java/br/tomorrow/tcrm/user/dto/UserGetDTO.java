package br.tomorrow.tcrm.user.dto;

import br.tomorrow.tcrm.enterprise.EnterpriseEntity;
import br.tomorrow.tcrm.user.UserEntity;

public class UserGetDTO {
    public String name;

    public static UserGetDTO fromEntity(UserEntity entity){
        UserGetDTO dto = new UserGetDTO();
        dto.name = entity.getName();

        return dto;
    }
}
