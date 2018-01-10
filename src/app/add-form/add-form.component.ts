import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User }from '../clases/user';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-add-form',
  templateUrl: './add-form.component.html',
  styleUrls: ['./add-form.component.css']
})
export class AddFormComponent implements OnInit {

  public usuario= new User();

  constructor(private dataService: UserServiceService,private router:Router) { }

  ngOnInit() {
  }

  addUser(){
    console.log(this.usuario);
    this.dataService
    .saveUser(this.usuario)
    .subscribe((data:User) => this.usuario,res => {
      console.log("adduser response");
      //this.router.navigate(["users"]);
    });
    this.router.navigate(["users"]);
  }
}
