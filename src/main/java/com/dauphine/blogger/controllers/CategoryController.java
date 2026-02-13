package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CategoryRequestDto;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/categories")
@Tag(name = "categories", description = "API des catégories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @Operation(summary = "Liste toutes les catégories", description = "Retourne la liste de toutes les catégories")
    @ApiResponse(responseCode = "200", description = "Liste des catégories")
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupère une catégorie par id")
    @ApiResponse(responseCode = "200", description = "Catégorie trouvée")
    @ApiResponse(responseCode = "404", description = "Catégorie non trouvée")
    public ResponseEntity<Category> getById(
            @Parameter(description = "Identifiant de la catégorie") @PathVariable UUID id) {
        return categoryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Crée une nouvelle catégorie")
    @ApiResponse(responseCode = "201", description = "Catégorie créée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    public ResponseEntity<Category> create(@Valid @RequestBody CategoryRequestDto dto) {
        Category category = categoryService.create(dto.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Met à jour le nom d'une catégorie")
    @ApiResponse(responseCode = "200", description = "Catégorie mise à jour")
    @ApiResponse(responseCode = "404", description = "Catégorie non trouvée")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    public ResponseEntity<Category> updateName(
            @Parameter(description = "Identifiant de la catégorie") @PathVariable UUID id,
            @Valid @RequestBody CategoryRequestDto dto) {
        return categoryService.updateName(id, dto.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime une catégorie")
    @ApiResponse(responseCode = "204", description = "Catégorie supprimée")
    @ApiResponse(responseCode = "404", description = "Catégorie non trouvée")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant de la catégorie") @PathVariable UUID id) {
        return categoryService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/posts")
    @Operation(summary = "Liste les posts d'une catégorie", description = "Retourne tous les posts de la catégorie donnée")
    @ApiResponse(responseCode = "200", description = "Liste des posts")
    @ApiResponse(responseCode = "404", description = "Catégorie non trouvée")
    public ResponseEntity<List<Post>> getPostsByCategoryId(
            @Parameter(description = "Identifiant de la catégorie") @PathVariable UUID id) {
        if (categoryService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categoryService.findPostsByCategoryId(id));
    }
}
