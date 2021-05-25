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
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {
    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;

    @Value("${spring.admin}")
    private String admin;

    public HomeController(PostService postService,UserService userService, CommentService commentService){
        this.postService = postService;
        this.userService = userService;
        this.commentService = commentService;
    }
    @GetMapping("/")
    public String mainPage(Model model){
        return "redirect:/home";
    }


    @GetMapping("/home")
    public String home(Model model){
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "/home";

    }
    @GetMapping("/userAdministration")
    public String userAdministration(Model model, Principal principal){
        if(principal != null){
            if(principal.getName().equals(admin)){
                List<Post> posts = postService.findAll();
                List<Comment> comments = commentService.findAll();
                model.addAttribute("posts", posts);
                model.addAttribute("comments", comments);
                return "/userAdministration";}
            else{
                User user  = userService.findByUsername(principal.getName()).get();
                List<Post> posts = postService.findByUser(user);
                List<Comment> comments = commentService.findByUser(user);
                model.addAttribute("posts", posts);
                model.addAttribute("comments", comments);
                return "/userAdministration";
            }

        }
        return "/home";


    }
}
