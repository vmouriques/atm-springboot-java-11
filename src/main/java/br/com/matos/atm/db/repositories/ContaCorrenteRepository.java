package br.com.matos.atm.db.repositories;

import br.com.matos.atm.db.entities.ContaCorrenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrenteEntity, Long> {
}
