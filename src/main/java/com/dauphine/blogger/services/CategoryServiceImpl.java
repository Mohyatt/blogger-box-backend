package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> categories = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final PostService postService;

    public CategoryServiceImpl(PostService postService) {
        this.postService = postService;
    }

    @Override
    public List<Category> findAll() {
        return List.copyOf(categories);
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categories.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public Category create(String name) {
        Category category = new Category(idGenerator.getAndIncrement(), name);
        categories.add(category);
        return category;
    }

    @Override
    public Optional<Category> updateName(Long id, String name) {
        return findById(id)
                .map(c -> {
                    c.setName(name);
                    return c;
                });
    }

    @Override
    public boolean deleteById(Long id) {
        return categories.removeIf(c -> c.getId().equals(id));
    }

    @Override
    public List<Post> findPostsByCategoryId(Long categoryId) {
        return postService.findAllByCategoryId(categoryId);
    }
}
