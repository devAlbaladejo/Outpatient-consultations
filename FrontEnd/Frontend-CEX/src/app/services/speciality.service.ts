import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SpecialityModel } from '../model/speciality-model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SpecialityService {

  constructor(private httpClient: HttpClient) {

  }

  getSpecialities() : Observable<SpecialityModel[]>{
    return this.httpClient.get<SpecialityModel[]>('http://localhost:8080/api/specialities').pipe(map(res => res));
  }
}
