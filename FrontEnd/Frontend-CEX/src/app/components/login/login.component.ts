import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserModel } from 'src/app/model/user-model';
import { UserService } from 'src/app/services/user.service';
import { UtilsService } from 'src/app/services/utils.service';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  user: UserModel;
  formLogin: FormGroup;

  constructor(private userService: UserService, private utilsService: UtilsService, private loginService: LoginService) {}

  ngOnInit(): void {
    this.formLogin = new FormGroup({
      email: new FormControl(''),
      password: new FormControl('')
    })
  }

  // Check if user credentials are valid
  login(){
    this.userService.getUser(this.formLogin.value.email, this.formLogin.value.password).subscribe(resp => {
      if(resp){
        this.user = resp;
        this.loginService.login(this.user);
      }
      else{
        this.utilsService.showAlert("Invalid Credentials","Error");
      }
    });
    
  }
}
