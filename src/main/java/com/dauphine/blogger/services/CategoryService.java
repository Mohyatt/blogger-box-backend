package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findById(UUID id);

    Category create(String name);

    Optional<Category> updateName(UUID id, String name);

    boolean deleteById(UUID id);

    List<Post> findPostsByCategoryId(UUID categoryId);
}
