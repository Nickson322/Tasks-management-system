package com.product.taskmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String priority;

    private String type;

    private String status;

    private LocalDate createdOn;

    private LocalDate updatedOn;

    private String timeSpent;

    private String description;


    //tasks <- users
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    //tasks <- users
    @ManyToOne
    @JoinColumn(name = "executor_id", referencedColumnName = "id")
    private User executor;

    //tasks <- projects
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    //tasks <- sprints
    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;
}
