import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserModel } from '../model/user-model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  constructor(private httpClient: HttpClient) {

  }

  getUser(email: string, password: string) : Observable<UserModel>{
    return this.httpClient.get<UserModel>('http://localhost:8080/api/users/' + email + "/" + password).pipe(map(res => res));
  }
}