import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ListingModel } from '../model/listing-model';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ListingService {

  constructor(private httpClient: HttpClient) { 

  }

  getListings() : Observable<ListingModel[]>{
    return this.httpClient.get<ListingModel[]>('http://localhost:8080/api/listings').pipe(map(res => res));
  }

  saveListing(request: any) : Observable<any>{
    return this.httpClient.post<any>('http://localhost:8080/api/listings/save', request).pipe(map(res => res));
  }

  updateListing(id: number, request: any) : Observable<any>{
    return this.httpClient.put<any>('http://localhost:8080/api/listings/update/' + id, request).pipe(map(res => res));
  }

  deleteListing(id: number) : Observable<any>{
    return this.httpClient.delete<any>('http://localhost:8080/api/listings/delete/' + id).pipe(map(res => res));
  }
}
