import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatNativeDateModule, MatOption } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSelectModule } from '@angular/material/select';
import { TaskService } from '../task.service';
import { ProjectServiceService } from '../project-service.service';
import { Task } from '../tasks';
import { Project } from '../project';



@Component({
  standalone: true,
  imports: [MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    CommonModule,
    ReactiveFormsModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatOption,
    MatSelectModule
  ],
  selector: 'app-create-task-modal',
  templateUrl: './create-task-modal.component.html',
  styleUrls: ['./create-task-modal.component.css']
})
export class CreateTaskModalComponent {
  taskForm: FormGroup;
  projects: any[] = []; // List of projects
  project!: Project;
  progressOptions: number[] = [0, 25, 50, 75, 100];


  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<CreateTaskModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private taskService: TaskService,
    private projectService: ProjectServiceService // Inject the ProjectService
  ) {
    this.taskForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      status: ['TODO', Validators.required],
      progress: [0, Validators.required],
      projectId: [data.projectId, Validators.required],
      createdDate: [new Date(), Validators.required],
      deadline: [new Date(), Validators.required]
    });

    // Fetch projects when component initializes
    this.projectService.getProjects().subscribe((projects: any[]) => {
      this.projects = projects;
    });
  }
  getProjectDetails(projectId: number): void {
    this.projectService.getProjectById(projectId).subscribe(data => {
      this.project = data;
    });
  }
  onSubmit(): void {
    if (this.taskForm.valid) {
      const projectId = this.taskForm.value.projectId;
      const selectedProject = this.projects.find(project => project.id === projectId);
  
      if (selectedProject) {
        const task = {
          ...this.taskForm.value,
          project: selectedProject
        };
  
        this.taskService.createTask(task).subscribe(
          response => {
            console.log('Task created successfully', response);
            this.dialogRef.close(response);  // Pass the response (or true) to indicate success
          },
          error => {
            console.error('Error creating task', error);
          }
        );
      } else {
        console.error('Selected project not found');
      }

    }
    // this.getProjectDetails(this.data.projectId); 
  }  

  closeModal(): void {
    this.dialogRef.close();
  }
}

