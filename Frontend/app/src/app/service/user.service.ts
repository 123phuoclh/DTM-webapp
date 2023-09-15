import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";


const USER_API = 'http://localhost:8080/dashboard/user';
@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }
  }
