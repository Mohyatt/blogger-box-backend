package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {

    List<Post> findAllOrderByCreatedAtDesc();

    Optional<Post> findById(UUID id);

    Post create(String title, String content, UUID categoryId);

    Optional<Post> update(UUID id, String title, String content, UUID categoryId);

    boolean deleteById(UUID id);

    List<Post> findAllByCategoryId(UUID categoryId);
}
