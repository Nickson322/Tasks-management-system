package com.product.taskmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "projects")
public class Project {
    @Id
    private Long id;

    private String name;

    private String description;

    private String leaderName;


    //project <-> users
    @ManyToMany(mappedBy = "projects")
    private List<User> users;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
