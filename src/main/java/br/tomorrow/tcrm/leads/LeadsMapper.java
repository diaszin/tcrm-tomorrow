package br.tomorrow.tcrm.leads;

import br.tomorrow.tcrm.leads.dto.LeadsCreateDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LeadsMapper {
    LeadsMapper INSTANCE = Mappers.getMapper(LeadsMapper.class);


    LeadsEntity leadsCreateDTOToEntity(LeadsCreateDTO dto);
}
