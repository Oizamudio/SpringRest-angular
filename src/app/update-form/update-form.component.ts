import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User }from '../clases/user';
import { DatosServiceService } from '../datos-service.service';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-update-form',
  templateUrl: './update-form.component.html',
  styleUrls: ['./update-form.component.css']
})
export class UpdateFormComponent implements OnInit {

  public usuario= new User();

  constructor(private dataService: UserServiceService,private datosService:DatosServiceService,private router:Router) { }

  ngOnInit() {
    this.usuario = this.datosService.getUser();
    console.log(this.usuario);
  }

  updateUser(){
    this.dataService
    .updateUser(this.usuario)
    .subscribe((data:User) => this.usuario,res => {
      console.log("update user response");
      //this.router.navigate(["users"]);
    });
    this.router.navigate(["users"]);
  }

}
