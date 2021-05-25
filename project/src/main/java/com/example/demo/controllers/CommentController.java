package com.example.demo.controllers;

import com.example.demo.models.Comment;
import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.services.CommentService;
import com.example.demo.services.PostService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class CommentController {
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
    @Value("${spring.admin}")
    private String admin;
    public CommentController(PostService postService, UserService userService, CommentService commentService) {
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @RequestMapping(value = "/createComm", method = RequestMethod.POST)
    public String createNewPost(@Valid Comment comment, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/editComment";
        }
        commentService.save(comment);
        return "redirect:/userAdministration";
    }

    @RequestMapping(value = "/createComment", method = RequestMethod.POST)
    public String createNewComment(@Valid Comment comment,
                                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "/createComment";

        } else {
            commentService.save(comment);
            return "redirect:/userAdministration";
        }
    }
    @RequestMapping(value = "/commentPost/{id}", method = RequestMethod.GET)
    public String createCommentForPost(@PathVariable Long id,
                                    Principal principal,
                                    Model model) {

        Optional<Post> post = postService.findById(id);

        if (post.isPresent()) {
            if(principal != null){
                Optional<User> user = userService.findByUsername(principal.getName());

                if (user.isPresent()) {
                    Comment comment = new Comment();
                    comment.setUser(user.get());
                    comment.setPost(post.get());
                    model.addAttribute("comment", comment);
                    return "/createComment";
                }
            }

            else{
                Comment comment = new Comment();
                comment.setUser(userService.findByUsername("Admin").get());
                comment.setPost(post.get());
                model.addAttribute("comment", comment);
                return "/createComment";
            }
        }

        else{
            return "/home";
        }
        return "/home";
    }
    @RequestMapping(value = "/editComment/{id}", method = RequestMethod.GET)
    public String editCommentWithId(@PathVariable Long id,
                                 Principal principal,
                                 Model model) {

        Optional<Comment> optionalComment = commentService.findById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            if (isPrincipalOwnerOfComment(principal, comment)) {
                model.addAttribute("comment", comment);
                return "/createComment";
            }

        }
        return "/home";
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    public String deleteCommentWithId(@PathVariable Long id,
                                   Principal principal) {

        Optional<Comment> optionalComment = commentService.findById(id);

        if (optionalComment.isPresent()) {
            Comment comment= optionalComment.get();

            if (isPrincipalOwnerOfComment(principal, comment)) {
                commentService.delete(comment);
                return "redirect:/userAdministration";
            }
        }
        return "redirect:/userAdministration";
    }
    private boolean isPrincipalOwnerOfComment(Principal principal, Comment comment) {
        return ((principal != null && principal.getName().equals(comment.getUser().getUsername())) || (principal != null && principal.getName().equals(admin)));
    }
}
