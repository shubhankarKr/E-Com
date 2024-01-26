import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { APIList } from 'src/app/constants/APIList';
import { ErrorHandlerService } from '../errorHandler/error-handler.service';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(
    private http: HttpClient,
    private apiList: APIList,
    private errorService: ErrorHandlerService
  ) {}

  getProductsList(): Observable<any> {
    return this.http
      .get(this.apiList.GET_ALL_PRODUCTS)
      .pipe(catchError(this.errorService.handleError));
  }
}
