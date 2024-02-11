import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpHandlerService } from '../httpHandler/http-handler.service';
import { APIList } from 'src/app/constants/APIList';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  constructor(private http: HttpHandlerService, private apiList: APIList) {}
  private cartCount = new BehaviorSubject<number>(0);

  findCart(): Observable<any> {
    return this.http.getMethod(this.apiList.CART_FIND);
  }

  updateCart(products: any[]): Observable<any> {
    return this.http.putMethod(this.apiList.CART_UPDATE, products);
  }
  add(products: any): Observable<any> {
    return this.http.postMethod(this.apiList.CART_ADD, products);
  }
  remove(products: any): Observable<any> {
    return this.http.postMethod(this.apiList.CART_REMOVE, products);
  }

  getCartCount() {
    return this.cartCount;
  }

  updateCartCount(value: number) {
    return this.cartCount.next(value);
  }
}
