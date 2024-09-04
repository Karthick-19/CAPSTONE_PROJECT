package com.example.ProjectManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProjectManagement.Entity.Project;
import com.example.ProjectManagement.Repository.ProjectRepository;

@Service
public class ProjectService {
	
	@Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow();
    }
    
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

}
