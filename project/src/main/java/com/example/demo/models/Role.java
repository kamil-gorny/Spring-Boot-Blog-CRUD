package com.example.demo.models;



import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="role_id")
    private Long id;
    @Column(name="role", unique=true)
    private String role;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Collection<User> users;
    

}
