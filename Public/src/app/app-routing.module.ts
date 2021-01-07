import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { Page404Component } from './components/page404/page404.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/auth/registration/registration.component';
import { VerifyComponent } from './components/auth/verify/verify.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegistrationWriterComponent } from './components/auth/registration-writer/registration-writer.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'registrate', component: RegistrationComponent},
  { path: 'registrateWriter', component: RegistrationWriterComponent },
  { path: 'verify/:token/:processId', component: VerifyComponent},
  { path: 'home', component: Page404Component }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
