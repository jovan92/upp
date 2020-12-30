import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { Page404Component } from './page404/page404.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { VerifyComponent } from './verify/verify.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'login', component: Page404Component},
  { path: 'registrate', component: RegistrationComponent},
  { path: 'verify/:token/:processId', component: VerifyComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
