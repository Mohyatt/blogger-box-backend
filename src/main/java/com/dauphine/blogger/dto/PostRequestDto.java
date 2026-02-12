package com.dauphine.blogger.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Requête de création ou mise à jour d'un post")
public class PostRequestDto {

    @NotBlank(message = "Le titre est obligatoire")
    @Schema(description = "Titre du post", example = "Mon premier article", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotBlank(message = "Le contenu est obligatoire")
    @Schema(description = "Contenu du post", requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @NotNull(message = "L'identifiant de catégorie est obligatoire")
    @Schema(description = "Identifiant de la catégorie", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long categoryId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
