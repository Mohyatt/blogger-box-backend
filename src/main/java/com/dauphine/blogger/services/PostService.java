package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.UUID;

public interface PostService {

    List<Post> findAllOrderByCreatedAtDesc();

    List<Post> getAll(String value);

    Post findById(UUID id);

    Post create(String title, String content, UUID categoryId);

    Post update(UUID id, String title, String content, UUID categoryId);

    void deleteById(UUID id);

    List<Post> findAllByCategoryId(UUID categoryId);
}
