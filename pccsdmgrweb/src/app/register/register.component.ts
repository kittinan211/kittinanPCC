import { Component, OnInit } from '@angular/core';
//import { AuthService } from '../_services/auth.service';
import { Employee } from '../services/employee';
import { EmployeeService } from '../services/employee.service'
import { TokenStorageService } from '../services/token-storage.service';
import { Router } from '@angular/router';
import { SignUpResponse } from '../services/SignUpResponse';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  isUsing2FA = false;
  errorMessage = '';
  qrCodeImage = '';
  employee: Employee;
  socialProvider: 'LOCAL';

  constructor(private authService: EmployeeService, private tokenStorage: TokenStorageService ,private router: Router) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.employee = this.form;
    this.authService.addEmployee(this.employee).subscribe(
      data => {
        if(data.using2FA){
        	this.isUsing2FA = true;
        	this.qrCodeImage = data.qrCodeImage;
        }
	    this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  loginPage(): void {
    this.router.navigate(['../../login']);
  }
}
