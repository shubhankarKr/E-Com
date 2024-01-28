import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, Observer, catchError } from 'rxjs';
import { APIList } from 'src/app/constants/APIList';
import { HttpHandlerService } from '../httpHandler/http-handler.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private loginFlag = new BehaviorSubject<boolean>(false);
  constructor(
    private http: HttpClient,
    private apiList: APIList,
    private httpHandler: HttpHandlerService
  ) {
    if (sessionStorage.getItem('token')) {
      this.loginFlag.next(true);
    } else {
      this.loginFlag.next(false);
    }
  }

  login(user: any) {
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.append(
      'Authorization',
      'Basic ' + window.btoa(user.userName + ':' + user.password)
    );
    return this.http
      .get(this.apiList.LOGIN_AUTHENTICATE, {
        headers: httpHeaders,
        withCredentials: true,
        observe: 'response',
      })
      .pipe(catchError(this.httpHandler.handleError));
  }

  signUp(data: any) {
    return this.http
      .post(this.apiList.LOGIN_REGISTER, data)
      .pipe(catchError(this.httpHandler.handleError));
  }

  loginUser(userName: string, token: string) {
    sessionStorage.setItem('currentUser', userName);
    sessionStorage.setItem('token', token);
    this.loginFlag.next(true);
  }

  logoutUser() {
    // console.log('logoutUser method called');
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('currentUser');
    this.loginFlag.next(false);
  }

  getLoginFlag(): Observable<boolean> {
    return this.loginFlag;
  }

  getCurrentUser() {
    return sessionStorage.getItem('currentUser');
  }
}
