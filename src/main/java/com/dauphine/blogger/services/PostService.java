package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> findAllOrderByCreatedAtDesc();

    Optional<Post> findById(Long id);

    Post create(String title, String content, Long categoryId);

    Optional<Post> update(Long id, String title, String content, Long categoryId);

    boolean deleteById(Long id);

    List<Post> findAllByCategoryId(Long categoryId);
}
