import { Injectable } from '@angular/core';
import {User } from './clases/user';

@Injectable()
export class DatosServiceService {
  private datosUsuario = new User();
 
  constructor() { }

   getUser(){
    return this.datosUsuario;
   }  
   setUser(usuario:User){
    this.datosUsuario = usuario;
   }

}
