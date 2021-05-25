package com.example.demo.models;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;


@Entity
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column(name = "title", nullable = false)
    @Length(min = 5, message = "Tytul musi miec conajmniej 5 znaków")
    @NotEmpty(message = "Tytul nie moze byc pusty")
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    @NotEmpty(message = "Zawartosc nie moze byc pusta")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @NotNull
    private User user;

    @OneToMany(mappedBy="post", cascade= CascadeType.REMOVE)
    private Collection<Comment> comments;



}
