package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> findAll();

    List<Category> getAll(String name);

    Category findById(UUID id);

    Category create(String name);

    Category updateName(UUID id, String name);

    void deleteById(UUID id);

    List<Post> findPostsByCategoryId(UUID categoryId);
}

