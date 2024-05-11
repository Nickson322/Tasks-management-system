package com.product.taskmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
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

    private ZonedDateTime executionPeriod;

    private ZonedDateTime createdOn;

    private ZonedDateTime updatedOn;

    private Duration timeSpent;

    private String description;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private User executor;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    //tasks <- sprints
    @ManyToOne
    @JoinColumn(name = "sprint_id")
    private Sprint sprint;
}
