import { Component, Input } from '@angular/core';
import { CartService } from 'src/app/service/cart/cart.service';

@Component({
  selector: 'app-custom-card',
  templateUrl: './custom-card.component.html',
  styleUrls: ['./custom-card.component.css'],
})
export class CustomCardComponent {
  constructor(private cartServcie: CartService) {}
  @Input() productList!: any[];
  getFinalPrice(price: number, discount: number) {
    return price - (price * discount) / 100;
  }

  addToCart(product: any) {
    this.cartServcie.add(product).subscribe({
      next: (res) => this.cartServcie.updateCartCount(res.products.length),
    });
  }
}
