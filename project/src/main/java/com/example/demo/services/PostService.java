package com.example.demo.services;

import com.example.demo.models.Post;
import com.example.demo.models.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> findById(Long id);
    Post save(Post post);
    void delete(Post post);
    List<Post> findAll();
    List<Post> findByUser(User user);
}
