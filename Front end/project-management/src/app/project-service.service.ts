import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, map, Observable, take, tap } from 'rxjs';
import { Project } from './project';

@Injectable({
  providedIn: 'root'
})
export class ProjectServiceService {
  projects!: Project
  private baseUrl = 'http://localhost:7001/projects';

  private projectsSubject = new BehaviorSubject<Project[]>([]);
  public projects$: Observable<Project[]> = this.projectsSubject.asObservable();


  constructor(private http: HttpClient) {
    this.fetchProjects();
  }


  // private fetchProjects(): void {
  //   this.http.get<Project[]>(this.baseUrl).subscribe(projects => {
  //     this.projectsSubject.next(projects);
  //   });
  // }
  private fetchProjects(): void {
    const userId = localStorage.getItem('userId');
    if (userId) {
      const userIdNumber = Number(userId);
      this.getProjectsByUserId(userIdNumber).subscribe(projects => {
        this.projectsSubject.next(projects);
      });
    }
  }

  
  
  createProject(project: Project): Observable<Project> {
    return this.http.post<Project>(this.baseUrl, project).pipe(
      tap(() => this.fetchProjects())  // Fetch the updated list for the specific user
    );
  }
  
  getAllProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.baseUrl);
  }

  getProject(id: any): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  // createProject(project: Project): Observable<Project> {
  //   return this.http.post<Project>(this.baseUrl, project).pipe(tap(() => this.fetchProjects())); 
  // }

  getProjectById(projectId: number): Observable<Project> {
    const url = `${this.baseUrl}/${projectId}`;
    return this.http.get<Project>(url);
  }
  getTasksByProject(projectId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}?projectId=${projectId}`);
  }
  getProjects(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }
  deleteProject(projectId: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${projectId}`).pipe(
      tap(() => this.fetchProjects()));
  }

  getProjectsx(): Project[] {
    return this.projectsSubject.getValue();
  }

  // Method to update the projects list
  updateProjects(projects: Project[]): void {
    this.projectsSubject.next(projects);
  }

  // Method to add a new project
  addProject(project: Project): void {
    const currentProjects = this.projectsSubject.getValue();
    this.projectsSubject.next([...currentProjects, project]);
  }

  // Method to delete a project
  // deleteProjectx(projectId: number): void {
  //   const currentProjects = this.projectsSubject.getValue();
  //   this.projectsSubject.next(currentProjects.filter(p => p.id !== projectId));
  // }
  removeProject(projectId: number): void {
    this.projects$.pipe(
      take(1), // Take the latest value from the observable
      map(projects => projects.filter(project => project.id !== projectId))
    ).subscribe(updatedProjects => this.projectsSubject.next(updatedProjects));
  }

  // getProjectsByUserId(userId: number): Observable<Project[]> {
  //   return this.http.get<Project[]>(`${this.baseUrl}/${userId}`);
  // }
  getProjectsByUserId(userId: number): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.baseUrl}/user/${userId}`);
  }
}
