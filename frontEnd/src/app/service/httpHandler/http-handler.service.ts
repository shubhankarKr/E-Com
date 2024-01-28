import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HttpHandlerService {
  constructor(private http: HttpClient) {}

  getMethod(api: string): Observable<any> {
    return this.http.get(api).pipe(catchError(this.handleError));
  }

  deleteMethod(api: string): Observable<any> {
    return this.http.delete(api).pipe(catchError(this.handleError));
  }

  putMethod(api: string, data: any): Observable<any> {
    return this.http.put(api, data).pipe(catchError(this.handleError));
  }

  postMethod(api: string, data: any): Observable<any> {
    return this.http.post(api, data).pipe(catchError(this.handleError));
  }

  handleError(error: HttpErrorResponse): Observable<any> {
    // console.log('error occured' + JSON.stringify(error));
    let errorMessage: string;
    if (error.status == 401) {
      errorMessage = error.error;
    } else if (error.status == 403) {
      errorMessage = 'You are not autherized';
    } else {
      if (error.error.errorResponse) {
        errorMessage = error.error.errorResponse;
      } else {
        errorMessage = error.error;
      }
    }
    return throwError(() => {
      return errorMessage;
    });
  }
}
