import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RoomModel } from '../model/room-model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private httpClient: HttpClient) {

  }

  getRooms() : Observable<RoomModel[]>{
    return this.httpClient.get<RoomModel[]>('http://localhost:8080/api/rooms').pipe(map(res => res));
  }

  updateRoom(id: number, request: any) : Observable<any>{
    return this.httpClient.put<any>('http://localhost:8080/api/rooms/update/' + id, request).pipe(map(res => res));
  }
}
