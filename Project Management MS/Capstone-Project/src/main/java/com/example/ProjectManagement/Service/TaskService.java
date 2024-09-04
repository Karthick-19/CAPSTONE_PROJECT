package com.example.ProjectManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjectManagement.Entity.Task;
import com.example.ProjectManagement.Entity.TaskStatus;
import com.example.ProjectManagement.Repository.TaskRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setStatus(status);
        return taskRepository.save(task);
    }

    public Task updateTaskProgress(Long taskId, int progress) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setProgress(progress);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByProject(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }
    
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow();
    }
}

