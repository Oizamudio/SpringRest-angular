import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';

import { User } from './clases/user';
import { Observable } from 'rxjs/Observable';
/* configuracion de la url del rest */
import { Configuration } from './configuration';

@Injectable()
export class UserServiceService {
  
  private actionUrl: string;

  constructor(private http:HttpClient, private config: Configuration) {
    this.actionUrl = config.ServerWithApiUrl;
  }
  /* PETICIONES */
  public getSingle<User>(id: number): Observable<User> {
    return this.http.get<User>(this.actionUrl + "/usersList/" + id);
  }
  public getAll<User>(): Observable<User> {
    return this.http.get<User>(this.actionUrl + "/usersList");
  }
  public deleteUserById(id: number): Observable<any> {
    return this.http.delete(this.actionUrl + '/delete/' + id);
  }
  public saveUser(user: User): Observable<any>{
    console.log(user);
    return this.http.post(this.actionUrl + "/adduser",user);/*.catch((error:any) => Observable.throw(error.json().error||'Server error'))*/ 
  }
  public updateUser(user: User): Observable<any> {
    return this.http.put(this.actionUrl + "/update/" + user.id,user);
  }
}

@Injectable()
export class CustomInterceptor implements HttpInterceptor {

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (!req.headers.has('Content-Type')) {
            req = req.clone({ headers: req.headers.set('Content-Type', 'application/json') });
        }

        req = req.clone({ headers: req.headers.set('Accept', 'application/json') });
        console.log(JSON.stringify(req.headers));
        return next.handle(req);
    }
}
