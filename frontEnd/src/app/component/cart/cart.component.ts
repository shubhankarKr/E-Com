import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/service/cart/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  constructor(private cartService: CartService) {}
  products!: any[];
  ngOnInit(): void {
    this.cartService.findCart().subscribe({
      next: (res) => (this.products = res.products),
    });
  }

  remove(p: any) {
    this.cartService.remove(p).subscribe({
      next: (res) => {
        this.cartService.updateCartCount(res.products.length);
        this.products = res;
      },
    });
  }
}
