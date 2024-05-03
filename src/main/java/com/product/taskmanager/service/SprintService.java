package com.product.taskmanager.service;

import com.product.taskmanager.dto.SprintCreateDTO;
import com.product.taskmanager.model.Sprint;
import com.product.taskmanager.repository.SprintRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SprintService {
    private final SprintRepository sprintRepository;

    public Sprint create(SprintCreateDTO sprintCreateDTO){
        Sprint sprint = Sprint.builder()
        //

                .build();

        return sprintRepository.save(sprint);
    }

    public List<Sprint> readAll(){
        return sprintRepository.findAll();
    }

    public Sprint update(Sprint sprint){
        return sprintRepository.save(sprint);
    }

    public void delete(Long id){
        sprintRepository.deleteById(id);
    }
}
