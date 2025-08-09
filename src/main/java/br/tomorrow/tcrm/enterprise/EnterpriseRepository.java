package br.tomorrow.tcrm.enterprise;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EnterpriseRepository extends CrudRepository<EnterpriseEntity, UUID> { }
