import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ErrorHandlerService {
  constructor() {}

  handleError(error: HttpErrorResponse): Observable<any> {
    console.log('error ' + JSON.stringify(error));
    let errorMessage: string;
    if (error.status == 401) {
      errorMessage = error.error;
    } else if (error.status == 403) {
      errorMessage = 'You are not autherized';
    } else {
      errorMessage = error.message;
    }
    return throwError(() => {
      return errorMessage;
    });
  }
}
