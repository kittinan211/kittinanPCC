import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { PccEmployeeComponent } from './pcc-employee/pcc-employee.component';
import { RegisterComponent } from './register/register.component';
import { TotpComponent } from './totp/totp.component';
import { NewsComponent } from './news/news.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'Employee', component: PccEmployeeComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'totp', component: TotpComponent },
  { path: 'News', component: NewsComponent },


  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
