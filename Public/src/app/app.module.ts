import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { Page404Component } from './page404/page404.component';
import { HomeComponent } from './home/home.component';
import { RegistrationComponent } from './registration/registration.component';
import { VerifyComponent } from './verify/verify.component';
import { SendMailComponent } from './send-mail/send-mail.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    Page404Component,
    HomeComponent,
    RegistrationComponent,
    VerifyComponent,
    SendMailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
