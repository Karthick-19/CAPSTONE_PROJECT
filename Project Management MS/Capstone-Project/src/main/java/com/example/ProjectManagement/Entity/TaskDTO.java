package com.example.ProjectManagement.Entity;

public class TaskDTO {
    private Long id;
    private String name;
    private String description;
    private TaskStatus status;
    private int progress;
    private Long projectId; // Only the ID of the project, not the whole object

    // Getters and Setters
    public TaskDTO() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
    
    
}
//CHAT GPT REFERENCE https://chatgpt.com/share/f604d658-6979-4940-adc6-d1b26034e964 

//Problem Recap
//You want to create a task and link it to a project using just the projectId. However, when you create a task and serialize the response, you end up with a recursive JSON structure, where the Task includes the full Project, and the Project again includes the Task, leading to an infinite loop.
//
//Solution Explanation
//1. Using DTO (Data Transfer Object)
//A DTO is a simple object that is used to transfer data between different layers of an application. In this case, you use a TaskDTO to capture only the necessary data (like projectId) when creating a task.
//
//Why Use a DTO?
//Separation of Concerns: It separates the internal structure of your entities (like Task and Project) from what is exposed to the client.
//Prevents Unwanted Data Exposure: You can control exactly what data is sent to and from the client, avoiding exposing sensitive or unnecessary details.
//Simplifies Data Handling: You don't need to pass the entire Project object, just the projectId.
//2. Controller Logic
//In the controller, you accept the TaskDTO and map it to a Task entity. Here’s what happens:
//
//Fetch the Project: The projectId is extracted from the TaskDTO, and the corresponding Project entity is fetched from the database.
//Set the Project on the Task: The fetched Project is then set on the Task entity.
//Save the Task: The Task entity, now linked to the Project, is saved to the database.
//
//Prevent Recursive Serialization
//The recursive problem occurs because the Task has a reference to Project, and Project has a reference back to Task. This mutual referencing leads to a recursive loop when converting the objects to JSON.
//
//Solution: @JsonManagedReference and @JsonBackReference
//These annotations help control how the references between objects are serialized:
//
//@JsonManagedReference: Used on the parent side (e.g., in Project), indicating that this side should be serialized normally.
//@JsonBackReference: Used on the child side (e.g., in Task), indicating that this side should not be serialized, breaking the loop.
//
//What Happens in the Backend
//When Creating a Task: The TaskDTO carries just the projectId. The backend fetches the Project based on this ID and associates it with the Task.
//When Fetching a Project: The Project will include its tasks, but each task will not redundantly include the entire Project object, preventing infinite loops in the JSON response.
//Summary
//DTOs simplify data transfer and prevent unnecessary data exposure.
//@JsonManagedReference and @JsonBackReference break the recursive serialization loop by controlling how the relationships between Task and Project are serialized.
//Linking by ID: The projectId is enough to link a task to a project without needing to send the entire project object in the request.

