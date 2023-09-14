import {Injectable} from "@angular/core";
import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {TokenStorageService} from "../service/token-storage.service";
import {Observable} from "rxjs";
import {performanceTimestampProvider} from "rxjs/internal/scheduler/performanceTimestampProvider";

const TOKEN_HEADER_KEY = 'Authorization' ;

@Injectable()
export class Interceptor implements HttpInterceptor {
  constructor(private token : TokenStorageService) {
  }
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = this.token.getToken();
    let authReq = req;
    if (token !== null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer' + token)})
    }
    return next.handle(authReq);
  }
}

export const InterceptorProvider = [{Provider : HTTP_INTERCEPTORS, useClass: Interceptor, multi : true}];
