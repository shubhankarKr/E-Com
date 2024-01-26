import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/product/product.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',

  styleUrls: ['./homepage.component.css'],
})
export class HomepageComponent implements OnInit {
  productList!: any[];
  ngOnInit(): void {
    this.getAllProducts();
  }
  constructor(private productService: ProductService) {}

  getAllProducts() {
    this.productService.getProductsList().subscribe({
      next: (res) => {
        console.log(' success ' + JSON.stringify(res));
        this.productList = res;
      },
      error: (err) => console.log(JSON.stringify(err)),
    });
  }
}
