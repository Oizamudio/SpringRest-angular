import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HttpClient } from 'selenium-webdriver/http';
/* Clases y Servicios */
import { DatosServiceService } from './datos-service.service';
import { UserServiceService } from './user-service.service';
import { Configuration } from './configuration';
/* Componentes */
import { AppComponent } from './app.component';
import { UsersComponent } from './users/users.component';
import { AddFormComponent } from './add-form/add-form.component';
import { UpdateFormComponent } from './update-form/update-form.component';
import { MainComponent } from './main/main.component';

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    AddFormComponent,
    UpdateFormComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot([
      {
        path:"users",
        component:UsersComponent,
        /*children: [ 
          {
            path: 'users/addForm',
            component: AddFormComponent
          }
             ]*/
      },{
        path:"main",
        component:MainComponent
      },
      {
        path:"users/addForm",
        component:AddFormComponent
      },
      {
        path:"users/updateForm",
        component:UpdateFormComponent
      }
    ])
  ],
  providers: [
    UserServiceService,
    DatosServiceService,
    Configuration
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
