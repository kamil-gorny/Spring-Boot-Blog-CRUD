package com.example.demo.services;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findByUser(User user);
    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    List<Comment> findAll();
    void delete(Comment comment);
}
