import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MetadataService } from 'src/app/service/metadata/metadata.service';
import { ProductService } from 'src/app/service/product/product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
})
export class AddProductComponent implements OnInit {
  addForm!: FormGroup;
  categories!: any[];
  selectedCategories: any[] = [];
  constructor(
    private fb: FormBuilder,
    private metadataService: MetadataService,
    private productService: ProductService
  ) {}
  ngOnInit(): void {
    this.metadataService.findAllCategoriesMD().subscribe({
      next: (res) => (this.categories = res),
    });
    this.addForm = this.fb.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required],
      discount: [''],
    });
  }

  add() {
    let data = {
      name: this.addForm.get('name')?.value,
      description: this.addForm.get('description')?.value,
      price: this.addForm.get('price')?.value,
      discount: this.addForm.get('discount')?.value,
      categoryList: this.selectedCategories,
    };
    console.log(' data : ', JSON.stringify(data));

    this.productService.addProduct(data).subscribe({
      next: (res) => console.log(' success ' + JSON.stringify(res.length)),
    });
  }

  getValue(event: any, i: any) {
    // console.log(' value ' + JSON.stringify(event.target.checked));
    if (event.target.checked) {
      // console.log(' selectedf ' + this.selectedCategories);

      this.selectedCategories.push(i);
    } else {
      // this.selectedCategories = this.selectedCategories.filter(
      //   (ele) => ele.id !== i.id
      // );
      this.selectedCategories.splice(this.selectedCategories.indexOf(i), 1);
    }
  }
}
