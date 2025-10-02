package br.com.itau.cat_api_bff.controller;

import br.com.itau.cat_api_bff.controller.dto.ApiResponse;
import br.com.itau.cat_api_bff.controller.dto.BreedResponse;
import br.com.itau.cat_api_bff.controller.dto.PaginationResponse;
import br.com.itau.cat_api_bff.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/breeds")
public class BreedController {

    @Autowired
    BreedService breedService;

    @GetMapping()
    public ResponseEntity<ApiResponse<BreedResponse>> getBreeds(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        Page<BreedResponse> breedPage = breedService.findAllBreeds(PageRequest.of(page, pageSize));
        List<BreedResponse> breedResponses = breedPage.getContent();

        PaginationResponse pagination = PaginationResponse.fromPage(breedPage);
        ApiResponse<BreedResponse> response = new ApiResponse<>(breedResponses, pagination);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/temperament/{temperament}")
    public ResponseEntity<List<BreedResponse>> getByTemperament(@PathVariable String temperament) {
        List<BreedResponse> breeds = breedService.findByTemperament(temperament)
                .stream()
                .map(BreedResponse::fromEntity)
                .collect(Collectors.toList());

        if (breeds.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(breeds);
    }

    @GetMapping("/origin/{origin}")
    public ResponseEntity<List<BreedResponse>> getByOrigin(@PathVariable String origin) {
        List<BreedResponse> breeds = breedService.findByOrigin(origin)
                .stream()
                .map(BreedResponse::fromEntity)
                .collect(Collectors.toList());

        if (breeds.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(breeds);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BreedResponse> getBreedById(@PathVariable String id) {
        return breedService.findBreedById(id)
                .map(BreedResponse::fromEntity)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Breed not found"));
    }
}