import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {UserDetail} from "../model/userDetail.model";
import {TokenStorageService} from "./token-storage.service";
import {Router} from "@angular/router";

const AUTH_API = 'http://localhost:8080/';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  // @ts-ignore
  isLoggedIn: boolean;

  constructor(private http: HttpClient, private tokenStorage: TokenStorageService, private router: Router) {
  }

  login(obj: UserDetail): Observable<any> {
    return this.http.post(AUTH_API + 'login', {
      username: obj.username,
      password: obj.password
    });

  }

  register(obj: UserDetail): Observable<any> {
    console.log(obj);
    return this.http.post(AUTH_API + 'register', {
      name: obj.name,
      email: obj.email,
      username: obj.username,
      password: obj.password,
    });
  }

  logout() {
    this.tokenStorage.signOut();
    if (this.tokenStorage.getUser() === null) {
      this.router.navigateByUrl('/login')
    }
  }
}
