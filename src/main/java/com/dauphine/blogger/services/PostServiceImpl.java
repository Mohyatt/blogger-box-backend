package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Post> findAllOrderByCreatedAtDesc() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public Optional<Post> findById(UUID id) {
        return postRepository.findById(id);
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId));

        Post post = new Post(
                UUID.randomUUID(),
                title,
                content,
                category,
                LocalDateTime.now()
        );
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> update(UUID id, String title, String content, UUID categoryId) {
        return postRepository.findById(id)
                .map(existing -> {
                    Category category = categoryRepository.findById(categoryId)
                            .orElseThrow(() -> new IllegalArgumentException("Category not found: " + categoryId));
                    existing.setTitle(title);
                    existing.setContent(content);
                    existing.setCategory(category);
                    return postRepository.save(existing);
                });
    }

    @Override
    public boolean deleteById(UUID id) {
        if (!postRepository.existsById(id)) {
            return false;
        }
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Post> findAllByCategoryId(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .map(postRepository::findAllByCategoryOrderByCreatedAtDesc)
                .orElse(List.of());
    }
}
