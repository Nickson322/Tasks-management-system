package com.product.taskmanager.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Accessors(chain = true)
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String userRole;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String password;

    //users <- team
    @ManyToOne()
    @JoinColumn(name = "team_name")
    private Team team;

    //users <-> projects
    @ManyToMany
    @JoinTable(
            name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    //users -> tasks
    @OneToMany(mappedBy = "author")
    private List<Task> authorTasks;

    //users -> tasks
    @OneToMany(mappedBy = "executor")
    private List<Task> executorTasks;
}
