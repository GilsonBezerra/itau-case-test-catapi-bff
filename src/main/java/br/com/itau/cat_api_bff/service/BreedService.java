package br.com.itau.cat_api_bff.service;

import br.com.itau.cat_api_bff.controller.dto.BreedResponse;
import br.com.itau.cat_api_bff.entity.BreedEntity;
import br.com.itau.cat_api_bff.repository.BreedRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BreedService {

    private final BreedRepository breedRepository;

    public BreedService(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    public void save(BreedEntity breedEntity) {
        breedRepository.save(breedEntity);
        log.info("Message consumed and saved: {}", breedEntity);
    }

    public Page<BreedResponse> findAllBreeds(PageRequest pageRequest) {
        var breeds = breedRepository.findAll(pageRequest);
        return breeds.map(BreedResponse::fromEntity);
    }

    public Optional<BreedEntity> findBreedById(String id) {
        return breedRepository.findById(id);
    }

    public List<BreedEntity> findByTemperament(String temperament) {
        log.info("Buscando raças com temperamento: {}", temperament);
        return breedRepository.findByTemperamentContainingIgnoreCase(temperament);
    }

    public List<BreedEntity> findByOrigin(String origin) {
        log.info("Buscando raças com origem: {}", origin);
        return breedRepository.findByOriginContainingIgnoreCase(origin);
    }

}
