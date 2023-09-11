import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from "../model/user.model";

const AUTH_API = 'http://localhost:8080/';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  httpOptions: any;
  // @ts-ignore
  isLoggedIn: boolean;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
  }

  login(obj: User): Observable<any> {
    return this.http.post(AUTH_API + 'login', {
      username: obj.username,
      password: obj.password
    }, this.httpOptions);
  }

  register(obj: User): Observable<any> {
    console.log(obj);
    return this.http.post(AUTH_API + 'register', {
      name: obj.name,
      email: obj.email,
      username: obj.username,
      password: obj.password,
    }, this.httpOptions);
  }
}
