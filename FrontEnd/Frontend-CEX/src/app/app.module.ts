import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AssistantComponent } from './components/assistant/assistant.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DoctorComponent } from './components/doctor/doctor.component';
import { RoomComponent } from './components/room/room.component';
import { ListingComponent } from './components/listing/listing.component';
import { DatePipe } from '@angular/common';
import { ListingHUBComponent } from './components/listing-hub/listing-hub.component';
import { LoginComponent } from './components/login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTabsModule } from '@angular/material/tabs';
import { CookieService } from 'ngx-cookie-service';
import {Select2Module} from 'ng-select2-component'; 

@NgModule({
  declarations: [
    AppComponent,
    AssistantComponent,
    DoctorComponent,
    RoomComponent,
    ListingComponent,
    ListingHUBComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
    MatSnackBarModule,
    MatTabsModule,
    Select2Module
  ],
  providers: [DatePipe, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
