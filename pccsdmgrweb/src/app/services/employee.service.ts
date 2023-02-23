import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from './employee';
import { loginEmployee } from './loginEmployee';
import { environment } from 'src/environments/environment';
import { AuthResponse } from './AuthResponse';
import { SignUpResponse } from '../services/SignUpResponse';
import { TokenStorageService } from '../services/token-storage.service';


@Injectable({providedIn: 'root'})
export class EmployeeService {
  private apiServerUrl = environment.apiBaseUrl;
  roles: string[] = [];

  constructor(private http: HttpClient ,private tokenStorage: TokenStorageService){}

  public getEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.apiServerUrl}/employee/all`);
  }

  public addEmployee(employee: Employee): Observable<SignUpResponse> {
    return this.http.post<SignUpResponse>(`${this.apiServerUrl}/employee/add`, employee);
  }

  public updateEmployee(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.apiServerUrl}/employee/update`, employee);
  }

  public deleteEmployee(employeeId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/employee/delete/${employeeId}`);
  }

  login(LoginEmployee : loginEmployee): Observable<AuthResponse> {
    console.log(LoginEmployee);
    return this.http.post<AuthResponse>(`${this.apiServerUrl}/employee/login`, LoginEmployee);
  }

  public verify(credentials): Observable<AuthResponse> {  
    this.roles = this.tokenStorage.getUser()
    return this.http.post<AuthResponse>(`${this.apiServerUrl}/employee/verify/${credentials.code}`,this.roles);
  }
  
}
