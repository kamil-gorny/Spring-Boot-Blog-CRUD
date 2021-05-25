package com.example.demo.models;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="comment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    @Column(name="content", columnDefinition = "TEXT")
    @NotEmpty(message="Komentarz nie moze byc pusty")
    private String content;

    @Column(name="username", columnDefinition="TEXT")
    private String username;

    @ManyToOne
    @JoinColumn(name="post_id", referencedColumnName = "post_id", nullable=false)
    @NotNull
    private Post post;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "user_id")
    private User user;
}
