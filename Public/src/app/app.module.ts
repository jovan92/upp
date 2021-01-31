import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/auth/login/login.component';
import { Page404Component } from './components/page404/page404.component';
import { HomeComponent } from './components/home/home.component';
import { RegistrationComponent } from './components/auth/registration/registration.component';
import { VerifyComponent } from './components/auth/verify/verify.component';
import { SendMailComponent } from './components/auth/send-mail/send-mail.component';
import { RegistrationWriterComponent } from './components/auth/registration-writer/registration-writer.component';
import { UserHomeComponent } from './components/auth-user/user-home/user-home.component';
import { HeaderComponent } from './components/auth-user/header/header.component';
import { MeniComponent } from './components/auth-user/meni/meni.component';


import { HttpErrorInterceptorInterceptor } from './hendler/http-error-interceptor.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    Page404Component,
    HomeComponent,
    RegistrationComponent,
    VerifyComponent,
    SendMailComponent,
    RegistrationWriterComponent,
    UserHomeComponent,
    HeaderComponent,
    MeniComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: HttpErrorInterceptorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
