import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export abstract class AbstractService<T> {
  protected baseUrl = '';

  httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(protected http: HttpClient) {}

  getAll(): Observable<T[]> {
    return this.http.get<T[]>(this.baseUrl);
  }

  get(id: number): Observable<T> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<T>(url);
  }

  create(element: T): Observable<T> {
    return this.http.post<T>(this.baseUrl, element, this.httpOptions);
  }

  update(id: number, element: T): Observable<T> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<T>(url, element, this.httpOptions);
  }

  delete(id: number): Observable<T> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<T>(url, this.httpOptions);
  }
}
