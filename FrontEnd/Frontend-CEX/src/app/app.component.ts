import { Component, OnInit } from '@angular/core';
import { LoginService } from './services/login.service';
import { Router } from '@angular/router';
import { UtilsService } from './services/utils.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'Frontend-CEX';
  user = '';
  role = '';
  url = '';

  constructor(private loginService: LoginService, private router: Router, private utilsService: UtilsService){}

  ngOnInit(): void {
    this.checkPermissions();
  }

  // Check if user has permissions to access resource
  checkPermissions(){
    this.url = window.location.href.substring(window.location.href.lastIndexOf('/') + 1);

    if(!this.isLoggedIn()){
      if(this.url != 'listing-hub'){
        this.router.navigate(['/login']);
      }
      else{
        this.router.navigate(['/listing-hub']);
      }
    }
    else{
      if(this.role == 'assistant' && (this.url == 'doctors' || this.url == 'assistants' || this.url == 'rooms')){
        this.router.navigate(['/listing']);
        this.utilsService.showAlert("You don't have permissions to access!", "Error");
      }
    }
  }

  // Gets the logged in user
  isLoggedIn(){
    this.user = this.loginService.getUser();
    this.role = this.loginService.getRole();
    return this.loginService.isLoggedIn();
  }

  // Logout
  logout(){
    return this.loginService.logout();
  }
}
