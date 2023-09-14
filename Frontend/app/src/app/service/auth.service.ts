import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from "../model/user.model";

const AUTH_API = 'http://localhost:8080/';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // @ts-ignore
  isLoggedIn: boolean;

  constructor(private http: HttpClient) {
  }

  login(obj: User): Observable<any> {
    return this.http.post(AUTH_API + 'login', {
      username: obj.username,
      password: obj.password
    }, {responseType: 'text'});

  }

  register(obj: User): Observable<any> {
    console.log(obj);
    return this.http.post(AUTH_API + 'register', {
      name: obj.name,
      email: obj.email,
      username: obj.username,
      password: obj.password,
    });
  }
}
