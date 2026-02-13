package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.PostRequestDto;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
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
@RequestMapping("/v1/posts")
@Tag(name = "posts", description = "API des posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(summary = "Liste tous les posts", description = "Retourne tous les posts triés par date de création (décroissant)")
    @ApiResponse(responseCode = "200", description = "Liste des posts")
    public ResponseEntity<List<Post>> getAll() {
        return ResponseEntity.ok(postService.findAllOrderByCreatedAtDesc());
    }

    @PostMapping
    @Operation(summary = "Crée un nouveau post")
    @ApiResponse(responseCode = "201", description = "Post créé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    public ResponseEntity<Post> create(@Valid @RequestBody PostRequestDto dto) {
        Post post = postService.create(dto.getTitle(), dto.getContent(), dto.getCategoryId());
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Met à jour un post existant")
    @ApiResponse(responseCode = "200", description = "Post mis à jour")
    @ApiResponse(responseCode = "404", description = "Post non trouvé")
    @ApiResponse(responseCode = "400", description = "Données invalides")
    public ResponseEntity<Post> update(
            @Parameter(description = "Identifiant du post") @PathVariable UUID id,
            @Valid @RequestBody PostRequestDto dto) {
        return postService.update(id, dto.getTitle(), dto.getContent(), dto.getCategoryId())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprime un post")
    @ApiResponse(responseCode = "204", description = "Post supprimé")
    @ApiResponse(responseCode = "404", description = "Post non trouvé")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Identifiant du post") @PathVariable UUID id) {
        return postService.deleteById(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
