import { Component, OnInit } from '@angular/core';
import { BehaviorSubject, Observable, Observer } from 'rxjs';
import { CartService } from 'src/app/service/cart/cart.service';
import { ProductService } from 'src/app/service/product/product.service';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  showLogin!: boolean;
  currentUser!: string | null;
  cartCount!: number;
  constructor(
    private userService: UserService,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.userService.getLoginFlag().subscribe({
      next: (res) => {
        this.showLogin = res;
        if (res) {
          this.cartService
            .getCartCount()
            .subscribe({ next: (res) => (this.cartCount = res) });
        }
        this.currentUser = this.userService.getCurrentUser();
      },
    });
  }

  logOut() {
    this.userService.logoutUser();
  }
}
