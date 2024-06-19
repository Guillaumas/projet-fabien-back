package fr.guigs.api.models;

import jakarta.persistence.*;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String password;
    private String username;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private Set<TodoList> todoLists;

    @OneToMany(mappedBy = "user")
    private Set<Label> labels;

    @Column(unique = true, nullable = false)
    private String auth0Id;

}
