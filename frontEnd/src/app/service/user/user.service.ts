import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, Observer, catchError } from 'rxjs';
import { APIList } from 'src/app/constants/APIList';
import { ErrorHandlerService } from '../errorHandler/error-handler.service';
import { User } from 'src/app/model/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private loggedInUser!: User;
  private loginFlag = new BehaviorSubject<boolean>(false);
  constructor(
    private http: HttpClient,
    private apiList: APIList,
    private errorHandler: ErrorHandlerService
  ) {
    this.loginFlag.next(false);
  }

  login(user: User) {
    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.append(
      'Authorization',
      'Basic ' + window.btoa(user.userName + ':' + user.getPassword())
    );
    return this.http
      .get(this.apiList.LOGIN_API, {
        headers: httpHeaders,
        withCredentials: true,
        observe: 'response',
      })
      .pipe(catchError(this.errorHandler.handleError));
  }

  setLoggedInUser(user: User) {
    this.loggedInUser = user;
  }
  getLoggedInUser(user: User): User {
    return this.loggedInUser;
  }

  loginUser() {
    // console.log('loginUser method called ');
    this.loginFlag.next(true);
  }

  logoutUser() {
    // console.log('logoutUser method called');
    this.loginFlag.next(false);
  }
  getLoginFlag(): Observable<boolean> {
    // console.log('getLoginFlag method called');
    return this.loginFlag;
  }
}
