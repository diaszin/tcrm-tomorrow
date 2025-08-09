package br.tomorrow.tcrm.enterprise;

import br.tomorrow.tcrm.enterprise.dto.EnterpriseCreateDTO;
import br.tomorrow.tcrm.enterprise.dto.EnterpriseGetAllDTO;
import br.tomorrow.tcrm.enterprise.dto.EnterpriseUpdateDTO;
import br.tomorrow.tcrm.enterprise.exceptions.EnterpriseIDNotValid;
import br.tomorrow.tcrm.enterprise.exceptions.EnterpriseNotFoundException;
import br.tomorrow.tcrm.enterprise.exceptions.UserNotCreateEnterprise;
import br.tomorrow.tcrm.user.UserEntity;
import br.tomorrow.tcrm.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EnterpriseService {
    @Autowired()
    private EnterpriseRepository enterpriseRepository;

    @Autowired()
    private UserService userService;

    public List<EnterpriseGetAllDTO> findAll() {
        List<EnterpriseEntity> enterpriseEntities = (List<EnterpriseEntity>) this.enterpriseRepository.findAll();
        return EnterpriseGetAllDTO.fromEntityList(enterpriseEntities);
    }

    public void create(UserEntity user, EnterpriseCreateDTO enterpriseCreateDTO) {
        EnterpriseEntity enterpriseEntity = EnterpriseMapper.INSTANCE.enterpriseCreateDTOToEnterpriseEntity(enterpriseCreateDTO);

        enterpriseEntity.creator = user;

        this.enterpriseRepository.save(enterpriseEntity);
    }

    public void delete(String uuid, UUID userID) {
        try {
            UUID id = UUID.fromString(uuid);
            EnterpriseEntity enterprise = this.enterpriseRepository.findById(id).orElseThrow(EnterpriseNotFoundException::new);
            if(!enterprise.creator.toString().equals(userID.toString())){
                throw new UserNotCreateEnterprise();
            }

            this.enterpriseRepository.deleteById(id);
        } catch (IllegalArgumentException e) {
            throw new EnterpriseIDNotValid();
        }
    }

    public void update(String uuid, EnterpriseUpdateDTO enterpriseUpdateDTO) {
        try {
            UUID id = UUID.fromString(uuid);
            EnterpriseEntity enterpriseEntity = this.enterpriseRepository.findById(id).orElseThrow(EnterpriseNotFoundException::new);
            EnterpriseMapper.INSTANCE.updateEnterpriseUpdateDTOtoEnterprise(enterpriseUpdateDTO, enterpriseEntity);

            this.enterpriseRepository.save(enterpriseEntity);
        } catch (IllegalArgumentException e) {
            throw new EnterpriseIDNotValid();
        }
    }

    public EnterpriseEntity findById(String id) {
        try{
            UUID uuid = UUID.fromString(id);
            return this.enterpriseRepository.findById(uuid).orElseThrow(EnterpriseNotFoundException::new);
        }
        catch (IllegalArgumentException e){
            throw new EnterpriseIDNotValid();
        }
    }
}
