import { Component, OnInit } from '@angular/core';

import { EmployeeService } from '../services/employee.service'
import { TokenStorageService } from '../services/token-storage.service';
import { AuthService } from '../services/auth.service';
import { Employee } from '../services/employee';
import { loginEmployee } from '../services/loginEmployee';
import { AuthResponse } from '../services/AuthResponse';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: any = {
    username: null,
    password: null
  };
  employee: Employee;
  LoginEmployee: loginEmployee;
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: EmployeeService, private tokenStorage: TokenStorageService,private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit(): void {
    this.LoginEmployee = this.form;
    
    this.authService.login(this.LoginEmployee).subscribe(
      data => {
        if(data.accessToken == "error"){
          this.errorMessage = "รหัสผ่านไม่ถูกต้อง";
          this.isLoginFailed = true;
        }else{
        if(data.authenticated == false){
          this.login(data);
        } else {
          this.tokenStorage.saveUser(data);
        	this.router.navigate(['/totp']);
        }
        }
      },
      err => {
        this.errorMessage = "ชื่อผู้ใช้ไม่ถูกต้อง";
        this.isLoginFailed = true;
      }
    );
    
  }

  reloadPage(): void {
    this.router.navigate(['../../Employee']);
  }

  registerPage(): void {
    this.router.navigate(['../../register']);
  }

  darkT(): void {
    document.documentElement.classList.add('dark');
  }

  login(data): void {
    this.tokenStorage.saveToken(data.accessToken);
      this.tokenStorage.saveUser(data);
      this.isLoginFailed = false;
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
      this.reloadPage();
    }
}
