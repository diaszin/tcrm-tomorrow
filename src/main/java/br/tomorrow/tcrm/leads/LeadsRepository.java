package br.tomorrow.tcrm.leads;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface LeadsRepository extends CrudRepository<LeadsEntity, UUID> {
    List<LeadsEntity> findAllByCreatorId (UUID uuid);
}
