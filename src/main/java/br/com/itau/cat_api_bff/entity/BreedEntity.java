package br.com.itau.cat_api_bff.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "breeds")
public class BreedEntity {

    @Id
    private String id;
    private String breed;
    private String origin;
    private String temperament;
    private String description;


}
