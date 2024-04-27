import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DiaryModel } from '../model/diary-model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class DiaryService {

  constructor(private httpClient: HttpClient) { 

  }

  getDiaries() : Observable<DiaryModel[]>{
    return this.httpClient.get<DiaryModel[]>('http://localhost:8080/api/diaries').pipe(map(res => res));
  }
}
