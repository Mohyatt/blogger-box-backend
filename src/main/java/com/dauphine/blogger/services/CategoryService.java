package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findById(Long id);

    Category create(String name);

    Optional<Category> updateName(Long id, String name);

    boolean deleteById(Long id);

    List<Post> findPostsByCategoryId(Long categoryId);
}
