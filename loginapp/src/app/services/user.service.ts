import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DashboardComponent } from '../components/dashboard/dashboard.component';
import { Invoice } from '../models/invoice';
import baseUrl from './helper'
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http:HttpClient
  ) { }

  //add user

  public addUser(user:any)
  {

    return this.http.post(`${baseUrl}/user/create`,user);

  }

  public getAllUsers(): Observable<any>{
    const header = new HttpHeaders().set('Authorization' , 'Bearer ' + localStorage.getItem('token'));
    return this.http.get<Invoice[]>(DashboardComponent.urls+`all`,{headers:header});
  }


  public getAllRoles(): Observable<any>{
    const header = new HttpHeaders().set('Authorization' , 'Bearer ' + localStorage.getItem('token') );
    return this.http.get<Invoice[]>(DashboardComponent.urls+`roles`,{headers:header});
  }
}
