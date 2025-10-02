package br.com.itau.cat_api_bff.controller.dto;

import br.com.itau.cat_api_bff.entity.BreedEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record BreedResponse(
        @JsonProperty("catApiId") String id,
        @JsonProperty("name") String breed,
        String origin,
        List<String> temperament,
        String description
) {
    public static BreedResponse fromEntity(BreedEntity entity) {
        List<String> temperamentList = entity.getTemperament() != null
                ? Arrays.stream(entity.getTemperament().split(","))
                .map(String::trim)
                .toList()
                : Collections.emptyList();

        return new BreedResponse(
                entity.getId(),
                entity.getBreed(),
                entity.getOrigin(),
                temperamentList,
                entity.getDescription()
        );
    }
}