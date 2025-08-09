package br.tomorrow.tcrm.enterprise;

import br.tomorrow.tcrm.enterprise.dto.EnterpriseCreateDTO;
import br.tomorrow.tcrm.enterprise.dto.EnterpriseUpdateDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE // Remove aviso de mapeamento
)
public interface EnterpriseMapper {
    EnterpriseMapper INSTANCE = Mappers.getMapper( EnterpriseMapper.class );

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEnterpriseUpdateDTOtoEnterprise(EnterpriseUpdateDTO dto, @MappingTarget EnterpriseEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EnterpriseEntity enterpriseCreateDTOToEnterpriseEntity(EnterpriseCreateDTO dto);
}
