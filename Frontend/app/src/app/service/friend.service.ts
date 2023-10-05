import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {FriendModel} from "../model/friend.model";

const BASE_URL = 'http://localhost:8080/friend'

@Injectable({providedIn: 'root'})
export class FriendService {
    constructor(private http: HttpClient) {
    }

    getFriend(name: string, userID: number, pageNo: number): Observable<any> {
        let param = new HttpParams();
        param = param.append("name", name as string);
        param = param.append("userID", userID as number);
        param = param.append("pageNo", pageNo as number);
        return this.http.get(BASE_URL, {params: param})
    }

    addFriend(obj: FriendModel): Observable<any> {
        return this.http.put(BASE_URL + '/add', obj)
    }

    deleteFriend(id : number): Observable<any> {
        return this.http.delete(BASE_URL + '/delete/' + id)
    }

    searchForFriend(name: string, userID: number, pageNo: number): Observable<any> {
      let param = new HttpParams();
      param = param.append("name", name as string);
      param = param.append("userID", userID as number);
      param = param.append("pageNo", pageNo as number);
      return  this.http.get("http://localhost:8080/dashboard/user/search", {params: param})
    }

    editFriend(obj: FriendModel) : Observable<any> {
      return this.http.post(BASE_URL + '/edit', obj)
    }
}
