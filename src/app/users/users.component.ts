import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import {User} from '../clases/user';
import { UserServiceService } from '../user-service.service';
import { DatosServiceService } from '../datos-service.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public usuario = new User();
  public users: User[];
  public titulo = "Usuarios";

  constructor(private UserService:UserServiceService,private DatosService:DatosServiceService,private router:Router) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers(){
    this.UserService
            .getAll<User[]>()
            .subscribe((data: User[]) => this.users = data,
            error => () => {
                console.log('error', 'Damn', 'Something went wrong...');
            },
            () => {
                console.log('success', 'Complete', 'Getting all values complete');
                console.log(this.users);
            });
  }

  deleteUser(id){
    this.usuario.setId(id);
    this.UserService
    .deleteUserById(this.usuario.id)
    .subscribe(
        res =>{
            this.getUsers();
        }
    );
  }
  modalinfo(id,name,lastname,mail,age,telephone){
    let usu = new User();
    usu.setId(id);
    usu.setName(name);
    usu.setLastName(lastname);
    usu.setMail(mail);
    usu.setAge(age);
    usu.setTelephone(telephone);
    this.DatosService.setUser(usu);
    this.router.navigate(['users/updateForm']);
  }

}
