package com.example.demo.services.impl;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
    @Override
    public List<Comment> findByUser(User user){
        return commentRepository.findByUser(user);
    }
    @Override
    public Comment save(Comment comment){
        return commentRepository.saveAndFlush(comment);
    }
    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }
    @Override
    public List<Comment> findAll() {return commentRepository.findAll();}

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
