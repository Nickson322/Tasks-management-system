package com.product.taskmanager.dto.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserReadDTO {
    private String name;
    private String userRole;
    private String email;

//    private List<ProjectDTO> projects;
//    private List<TaskDTO> authorTasks;
//    private List<TaskDTO> executorTasks;
}
