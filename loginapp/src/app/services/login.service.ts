import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http'
import { Router } from '@angular/router';
import baseUrl from './helper';
import { User } from '../models/user';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  url="http://localhost:8080"
  userStr: string;
  constructor(private http:HttpClient , private router : Router) { }


  //calling the server to generate token

  public generateToken(loginData:any){
      //token generate
      return this.http.post(`${this.url}/generate-token`,loginData);
      
    }





  //for login user
  // public loginUser(token)
  // {
    
  //   localStorage.setItem('token',token);
  
  //   return true;
  // }
//to check that is logged in or not
  public isLoggedIn()
  {
    
   let tokenStr =  localStorage.getItem("token");
   if(tokenStr==undefined || tokenStr == '' || tokenStr ==null )
   
   {
     return false;
   }else{

     return true;
   }
  }
// for logout the user
  public logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.userStr
    return true;
  }

  //for getting the toke

  public getToken()
  {
    return localStorage.getItem('token');

    
  }

  //set userDetail
  public setUser(user: any)
  {
    localStorage.setItem('user',JSON.stringify(user));
    console.log(user);
  }
  public getUser()
  {
    this.userStr=localStorage.getItem('user');
    return JSON.parse(this.userStr);
  
  }
  public getUserRole(){
    let user = this.getUser()
    return user.authorities[0].authority;

  }

  //current user : which is logged in .
  public getCurrentUser(username: string)
  {

    return this.http.get(`${baseUrl}/user/${username}`);

  }

  public isAdmin() {
    return this.isLoggedIn() && this.getUserRole() === 'ADMIN';
}


}
