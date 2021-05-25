package com.example.demo.models;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Collection;


@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name="firstName", nullable=false)
    @NotEmpty(message="Imię nie może byc puste")
    private String firstName;

    @Column(name="lastName", nullable=false)
    @NotEmpty(message="Nazwisko nie może byc puste")
    private String lastName;

    @Column(name="email", unique=true, nullable=false)
    @Email(message = "Wprowadź poprawny Email")
    @NotEmpty(message = "Email nie może byc pusty")
    private String email;

    @Column(name="password", nullable=false)
    @NotEmpty(message = "Haslo nie moze byc puste")
    @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
            message = "Hasło musi mieć conajmniej 8 znaków, posiadać jeden znak specjalny, jedną wielką literę, jedną małą literę i jedną cyfrę ")
    private String password;

    @Column(name = "active", nullable = false)
    private int active;

    @Column(name="username", nullable = false)
    private String username;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "user")
    private Collection<Post> posts;


}
