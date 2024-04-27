import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DoctorModel } from '../model/doctor-model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private httpClient: HttpClient) { 

  }

  getDoctors() : Observable<DoctorModel[]>{
    return this.httpClient.get<DoctorModel[]>('http://localhost:8080/api/doctors').pipe(map(res => res));
  }

  saveDoctor(request: any) : Observable<any>{
    return this.httpClient.post<any>('http://localhost:8080/api/doctors/save', request).pipe(map(res => res));
  }

  updateDoctor(id: number, request: any) : Observable<any>{
    return this.httpClient.put<any>('http://localhost:8080/api/doctors/update/' + id, request).pipe(map(res => res));
  }

  deleteDoctor(id: number) : Observable<any>{
    return this.httpClient.delete<any>('http://localhost:8080/api/doctors/delete/' + id).pipe(map(res => res));
  }
}
