import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../model/user.model";


const USER_API = 'http://localhost:8080/dashboard/user/';

@Injectable({  providedIn: 'root'})
export class UserService {
  httpOptions: any;

  constructor(private http: HttpClient) {
    this.httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      }),
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
  }

  edit(obj: UserModel): Observable<any> {
    return this.http.put(USER_API + 'edit', {
        id: obj.id,
        email: obj.email,
        name: obj.name,
        nickName: obj.nickName,
        address: obj.address,
        phoneNumber: obj.phoneNumber,
        username: obj.username
      }, this.httpOptions
    )
  }
}
