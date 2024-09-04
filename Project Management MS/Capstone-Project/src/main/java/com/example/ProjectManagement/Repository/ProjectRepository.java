package com.example.ProjectManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ProjectManagement.Entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}

