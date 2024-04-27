import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AssistantModel } from '../model/assistant-model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AssistantService {

  constructor(private httpClient: HttpClient) {

  }

  getAssistants() : Observable<AssistantModel[]>{
    return this.httpClient.get<AssistantModel[]>('http://localhost:8080/api/assistants').pipe(map(res => res));
  }

  saveAssistant(request: any) : Observable<any>{
    return this.httpClient.post<any>('http://localhost:8080/api/assistants/save', request).pipe(map(res => res));
  }

  updateAssistant(id: number, request: any) : Observable<any>{
    return this.httpClient.put<any>('http://localhost:8080/api/assistants/update/' + id, request).pipe(map(res => res));
  }

  deleteAssistant(id: number) : Observable<any>{
    return this.httpClient.delete<any>('http://localhost:8080/api/assistants/delete/' + id).pipe(map(res => res));
  }
}
