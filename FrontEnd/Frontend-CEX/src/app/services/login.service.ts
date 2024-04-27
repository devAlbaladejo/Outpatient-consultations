import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

import { CookieService } from 'ngx-cookie-service';
import { UserModel } from '../model/user-model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  token: string;

  constructor(private router: Router, private cookie: CookieService) { }

  // Saves user data logged in
  // @param user: User logged in 
  login(user: UserModel){
    this.token = user.token;
    this.cookie.set("token",this.token);
    this.cookie.set("user",user.name);
    this.cookie.set("role",user.role.name);
    this.router.navigate(['/listing']);
  }

  // Get username
  getUser(){
    return this.cookie.get("user");
  }

  // Get user role
  getRole(){
    return this.cookie.get("role");
  }

  // Get user logged in token
  isLoggedIn(){
    return this.cookie.get("token");
  }

  // Clean user logged in data
  logout(){
    this.token = "";
    this.cookie.set("token",this.token);
    this.cookie.set("user","");
    this.cookie.set("role","");
    this.router.navigate(['/login']);
  }
}
