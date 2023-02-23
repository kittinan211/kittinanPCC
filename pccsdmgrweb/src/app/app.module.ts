import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeeService } from '../app/services/employee.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { PccEmployeeComponent } from './pcc-employee/pcc-employee.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import { RegisterComponent } from './register/register.component';
import { TotpComponent } from './totp/totp.component';
import { DatePipe } from '@angular/common'



//import { MatSnackBarModule } from '@angular/material/snack-bar';
//import { JwtModule } from '@auth0/angular-jwt';
import { LoginComponent } from './login/login.component';
import { NewsComponent } from './news/news.component';


// specify the key where the token is stored in the local storage
export const LOCALSTORAGE_TOKEN_KEY = 'angular-pccth-login';

// specify tokenGetter for the angular jwt package
export function tokenGetter() {
  return localStorage.getItem(LOCALSTORAGE_TOKEN_KEY);
}


@NgModule({
  declarations: [			
    AppComponent,
    LoginComponent,
    PccEmployeeComponent,
      LoginComponent,RegisterComponent,TotpComponent,
      NewsComponent
   ],
  imports: [
    BrowserModule,
    HttpClientModule, FormsModule,AppRoutingModule,
    BrowserAnimationsModule, 
    MatCardModule,
    MatFormFieldModule,
  ],
  providers: [EmployeeService,DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
