import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './services/token-storage.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public Token = false;

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) { 
        this.Token = true;
    }
  }
  constructor(private tokenStorage: TokenStorageService ,private router: Router) { }

  NewsPage(): void {
    this.router.navigate(['../../News']);
  }

  public DeletToken(): void {
    this.tokenStorage.removeToken();
    this.tokenStorage.signOut();
    window.location.reload();
  }
  reloadPage(): void {
    this.router.navigate(['../../Employee']);
  }
  
}
