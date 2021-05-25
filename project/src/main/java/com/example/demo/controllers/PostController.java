package com.example.demo.controllers;


import com.example.demo.models.Post;
import com.example.demo.models.User;
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
public class PostController {
    private final PostService postService;
    private final UserService userService;
    @Value("${spring.admin}")
    private String admin;
    public PostController(PostService postService, UserService userService){
        this.postService = postService;
        this.userService = userService;
    }
    @RequestMapping(value = "/createPost", method = RequestMethod.GET)
    public String newPost(Principal principal,
                          Model model) {
            Optional<User> user = userService.findByUsername(principal.getName());
            Post post = new Post();
            post.setUser(user.get());
            model.addAttribute("post", post);
            return "/createPost";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public String deletePostWithId(@PathVariable Long id,
                                   Principal principal) {

        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (isPrincipalOwnerOfPost(principal, post)) {
                postService.delete(post);
                return "redirect:/userAdministration";
            }
        }
        return "redirect:/userAdministration";
    }

    @RequestMapping(value = "/createPost", method = RequestMethod.POST)
    public String createNewPost(@Valid Post post, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/createPost";
        }
        postService.save(post);
        return "redirect:/userAdministration";
    }
    @RequestMapping(value = "/editPost/{id}", method = RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id,
                                 Principal principal,
                                 Model model) {

        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (isPrincipalOwnerOfPost(principal, post)) {
                model.addAttribute("post", post);
                return "/createPost";
            }

        }
            return "/home";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public String getPostWithId(@PathVariable Long id,
                                Principal principal,
                                Model model) {

        Optional<Post> optionalPost = postService.findById(id);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            if (isPrincipalOwnerOfPost(principal, post)) {
                model.addAttribute("username", principal.getName());
            }

            return "/post";

        }
        return "redirect:/home";
    }

    private boolean isPrincipalOwnerOfPost(Principal principal, Post post) {
        return (principal != null && principal.getName().equals(post.getUser().getUsername())) || (principal != null && principal.getName().equals(admin));
    }

}
