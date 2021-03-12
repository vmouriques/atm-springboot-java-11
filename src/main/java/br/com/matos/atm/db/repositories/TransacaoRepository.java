package br.com.matos.atm.db.repositories;

import br.com.matos.atm.db.entities.TransacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<TransacaoEntity, Long> {
}
