import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';
import { LoginService } from 'src/app/services/login.service';
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  users:Array<User> = [];
  roles:Array<any> = [];
  auth:Array<any>;
  x = 47;
  currentUser;

  static urls = "http://localhost:8080/"

  constructor(private userService:UserService, private login:LoginService) { }

  ngOnInit(): void {

    
    this.currentUser = JSON.parse(localStorage.getItem('user'));
    console.log(this.currentUser);
    this.userService.getAllUsers().subscribe(data =>{

      this.auth = data.authorities;
      this.users = data;


      console.log(this.users)

    })
    this.userService.getAllRoles().subscribe(data=>{

      this.roles=data;


    })
  
  }


}
