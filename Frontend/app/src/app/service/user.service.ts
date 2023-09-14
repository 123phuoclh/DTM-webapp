import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {UserDetailModel} from "../model/userDetail.model";


const USER_API = 'http://localhost:8080/dashboard/user';
@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }
  getUserByID(obj : UserDetailModel) {
    return this.http.get(USER_API)
      }
  }
