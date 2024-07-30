package com.johnny.teste.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnny.teste.models.Task;
import com.johnny.teste.models.User;
import com.johnny.teste.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }

    @Transactional
    public Task create(Task obj) {
        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj; 
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(obj);
        
    }

    public void delete(Task obj) {
        findById(obj.getId());
        try {
            this.taskRepository.deleteById(obj.getId());
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel exlcuir pois há entidades relacionadas");
        }
    } 

}
