import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RoleModel } from '../model/role-model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private httpClient: HttpClient) { }

  getRoles() : Observable<RoleModel[]>{
    return this.httpClient.get<RoleModel[]>('http://localhost:8080/api/roles').pipe(map(res => res));
  }
}
