import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/auth/login/login.component';
import { Page404Component } from './components/page404/page404.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/auth/registration/registration.component';
import { VerifyComponent } from './components/auth/verify/verify.component';
import { SendMailComponent } from './components/auth/send-mail/send-mail.component';
import { RegistrationWriterComponent } from './components/auth/registration-writer/registration-writer.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    Page404Component,
    HomeComponent,
    RegistrationComponent,
    VerifyComponent,
    SendMailComponent,
    RegistrationWriterComponent
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
