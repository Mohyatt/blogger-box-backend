package com.dauphine.blogger.services;

import com.dauphine.blogger.exceptions.ResourceNotFoundException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    public List<Post> getAll(String value) {
        if (value == null || value.isBlank()) {
            return postRepository.findAllByOrderByCreatedAtDesc();
        }
        return postRepository
                .findByTitleContainingIgnoreCaseOrContentContainingIgnoreCaseOrderByCreatedAtDesc(
                        value, value
                );
    }

    @Override
    public Post findById(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", id.toString()));
    }

    @Override
    public Post create(String title, String content, UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", categoryId.toString()));

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
    public Post update(UUID id, String title, String content, UUID categoryId) {
        Post existing = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", id.toString()));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", categoryId.toString()));

        existing.setTitle(title);
        existing.setContent(content);
        existing.setCategory(category);
        return postRepository.save(existing);
    }

    @Override
    public void deleteById(UUID id) {
        if (!postRepository.existsById(id)) {
            throw new ResourceNotFoundException("Post", id.toString());
        }
        postRepository.deleteById(id);
    }

    @Override
    public List<Post> findAllByCategoryId(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", categoryId.toString()));
        return postRepository.findAllByCategoryOrderByCreatedAtDesc(category);
    }
}
