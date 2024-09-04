package com.example.ProjectManagement.Resources;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProjectManagement.Entity.Project;
import com.example.ProjectManagement.Entity.Task;
import com.example.ProjectManagement.Entity.TaskStatus;
import com.example.ProjectManagement.Repository.ProjectRepository;
import com.example.ProjectManagement.Repository.TaskRepository;
import com.example.ProjectManagement.Service.TaskService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private ProjectRepository projectRepository;

//    @PostMapping
//    public ResponseEntity<Task> createTask(@RequestBody Task task) {
//        return ResponseEntity.ok(taskService.createTask(task));
//    }
    
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Project project = projectRepository.findById(task.getProject().getId())
            .orElseThrow();

        task.setProject(project);
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.ok(savedTask);
    }
//    @PostMapping
//    public ResponseEntity<Task> createTask(@RequestBody Task task) {
//      // Fetch the Project entity based on the project ID provided
//      Optional<Project> project = projectRepository.findById(task.getProject().getId());
//      if (project == null) {
//        return ResponseEntity.badRequest().body(null); // Or handle this case as needed
//      }
//      
//      // Set the fetched Project to the Task
//      task.setProject(project);
//      
//      Task createdTask = taskService.createTask(task);
//      return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
//    }


    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        return ResponseEntity.ok(taskService.updateTask(task));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestParam TaskStatus status) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, status));
    }

    @PutMapping("/{id}/progress")
    public ResponseEntity<Task> updateTaskProgress(@PathVariable Long id, @RequestParam int progress) {
        return ResponseEntity.ok(taskService.updateTaskProgress(id, progress));
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Task>> getTasksByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProject(projectId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
}


