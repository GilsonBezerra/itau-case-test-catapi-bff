package br.com.itau.cat_api_bff.repository;

import br.com.itau.cat_api_bff.entity.BreedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BreedRepository extends JpaRepository<BreedEntity, String> {

    List<BreedEntity> findByTemperamentContainingIgnoreCase(String temperament);

    List<BreedEntity> findByOriginContainingIgnoreCase(String origin);
}
