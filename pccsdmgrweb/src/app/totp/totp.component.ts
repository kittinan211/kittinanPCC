import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../services/employee.service'
import { TokenStorageService } from '../services/token-storage.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-totp',
  templateUrl: './totp.component.html',
  styleUrls: ['./totp.component.css']
})
export class TotpComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  currentUser: any;

  constructor(private authService: EmployeeService, private tokenStorage: TokenStorageService,private router: Router) {}

  ngOnInit(): void {
  	if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.currentUser = this.tokenStorage.getUser();
    }
  }

  onSubmit(): void {
    this.authService.verify(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.login(data);
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  login(user): void {
	this.tokenStorage.saveUser(user);
	this.isLoginFailed = false;
	this.isLoggedIn = true;
	this.currentUser = this.tokenStorage.getUser();
  this.reloadPage();
  }

  reloadPage(): void {
    window.location.reload();
  }
}
