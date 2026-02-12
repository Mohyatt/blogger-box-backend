package com.dauphine.blogger.services;

import com.dauphine.blogger.models.Post;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final List<Post> posts = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public List<Post> findAllOrderByCreatedAtDesc() {
        return posts.stream()
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Post> findById(Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Post create(String title, String content, Long categoryId) {
        Post post = new Post(
                idGenerator.getAndIncrement(),
                title,
                content,
                categoryId,
                LocalDate.now()
        );
        posts.add(post);
        return post;
    }

    @Override
    public Optional<Post> update(Long id, String title, String content, Long categoryId) {
        return findById(id)
                .map(p -> {
                    p.setTitle(title);
                    p.setContent(content);
                    p.setCategoryId(categoryId);
                    return p;
                });
    }

    @Override
    public boolean deleteById(Long id) {
        return posts.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public List<Post> findAllByCategoryId(Long categoryId) {
        return posts.stream()
                .filter(p -> p.getCategoryId().equals(categoryId))
                .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
