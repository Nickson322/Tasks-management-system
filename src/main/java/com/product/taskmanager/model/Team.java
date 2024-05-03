package com.product.taskmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name = "teams")
public class Team {
    @Id
    private String name;

    private String description;


    //teams -> users
    @JsonManagedReference
    @OneToMany(mappedBy = "team")
    private List<User> users;

    //teams -> sprints
    @OneToMany(mappedBy = "team")
    private List<Sprint> sprints;

}
