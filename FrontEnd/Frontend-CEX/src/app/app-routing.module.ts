import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RoomComponent } from './components/room/room.component';
import { AssistantComponent } from './components/assistant/assistant.component';
import { DoctorComponent } from './components/doctor/doctor.component';
import { ListingComponent } from './components/listing/listing.component';
import { ListingHUBComponent } from './components/listing-hub/listing-hub.component';

const routes: Routes=[
  {path:'login', component: LoginComponent},
  {path:'listing', component: ListingComponent},
  {path:'doctors', component: DoctorComponent},
  {path:'assistants', component: AssistantComponent},
  {path:'rooms', component: RoomComponent},
  {path:'listing-hub', component: ListingHUBComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
