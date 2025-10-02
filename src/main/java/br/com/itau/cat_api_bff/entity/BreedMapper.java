package br.com.itau.cat_api_bff.entity;


import br.com.itau.cat_api_bff.controller.dto.BreedResponse;

public class BreedMapper {
    public static BreedEntity toEntity(BreedResponse dto) {
        if (dto.id() == null || dto.id().isBlank()) {
            throw new IllegalArgumentException("O campo 'id' não pode ser nulo ou vazio!");
        }
        BreedEntity entity = new BreedEntity();
        entity.setId(dto.id());
        entity.setBreed(dto.breed());
        entity.setOrigin(dto.origin());
        entity.setTemperament(
                dto.temperament() != null ? String.join(", ", dto.temperament()) : null
        );
        entity.setDescription(dto.description());
        return entity;
    }
}

