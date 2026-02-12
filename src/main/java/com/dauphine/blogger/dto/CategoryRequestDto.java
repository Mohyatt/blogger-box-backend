package com.dauphine.blogger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Requête de création ou mise à jour d'une catégorie")
public class CategoryRequestDto {

    @NotBlank(message = "Le nom est obligatoire")
    @Schema(description = "Nom de la catégorie", example = "Technologie", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
