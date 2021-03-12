package br.com.matos.atm.db.repositories;

import br.com.matos.atm.db.entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
}
