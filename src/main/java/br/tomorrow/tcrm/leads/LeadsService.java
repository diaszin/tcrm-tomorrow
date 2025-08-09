package br.tomorrow.tcrm.leads;

import br.tomorrow.tcrm.enterprise.EnterpriseEntity;
import br.tomorrow.tcrm.enterprise.EnterpriseRepository;
import br.tomorrow.tcrm.enterprise.EnterpriseService;
import br.tomorrow.tcrm.leads.dto.LeadsCreateDTO;
import br.tomorrow.tcrm.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LeadsService {
    @Autowired
    LeadsRepository leadsRepository;

    @Autowired
    EnterpriseService enterpriseService;

    public void create(UserEntity user, LeadsCreateDTO createDTO){

        EnterpriseEntity enterprise = this.enterpriseService.findById(createDTO.enterpriseID);
        LeadsEntity lead = LeadsMapper.INSTANCE.leadsCreateDTOToEntity(createDTO);

        lead.creator = user;
        lead.enterprise = enterprise;

        this.leadsRepository.save(lead);
    }


    public List<LeadsEntity> getAll() {
        return (List<LeadsEntity>) this.leadsRepository.findAll();
    }

    public List<LeadsEntity> getAllByUserID(UUID id) {
        return this.leadsRepository.findAllByCreatorId(id);
    }
}
