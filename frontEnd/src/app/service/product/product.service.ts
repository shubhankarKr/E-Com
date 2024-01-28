import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError } from 'rxjs';
import { APIList } from 'src/app/constants/APIList';
import { HttpHandlerService } from '../httpHandler/http-handler.service';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(
    private http: HttpClient,
    private apiList: APIList,
    private httpHandler: HttpHandlerService
  ) {}

  getProductsList(): Observable<any> {
    return this.httpHandler
      .getMethod(this.apiList.PRODUCTS_FIND_ALL)
      .pipe(catchError(this.httpHandler.handleError));
  }
}
