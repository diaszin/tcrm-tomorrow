package br.tomorrow.tcrm.enterprise.dto;

import br.tomorrow.tcrm.enterprise.EnterpriseEntity;
import br.tomorrow.tcrm.user.dto.UserGetDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EnterpriseGetAllDTO {
    public UUID id;
    public String name;
    public String description;
    public UserGetDTO creator;

    public static EnterpriseGetAllDTO fromEntity(EnterpriseEntity entity){
        EnterpriseGetAllDTO dto = new EnterpriseGetAllDTO();
        dto.name = entity.name;
        dto.description = entity.description;
        dto.id = entity.id;
        dto.creator = UserGetDTO.fromEntity(entity.creator);

        return dto;
    }

    public static List<EnterpriseGetAllDTO> fromEntityList(List<EnterpriseEntity> enterpriseEntityList){
        return enterpriseEntityList.parallelStream().map(EnterpriseGetAllDTO::fromEntity).toList();
    }
}
