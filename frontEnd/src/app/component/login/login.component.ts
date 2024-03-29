import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CartService } from 'src/app/service/cart/cart.service';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  errorMessage!: string;

  constructor(
    private userService: UserService,
    private router: Router,
    private cartService: CartService
  ) {}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      userName: new FormControl('', Validators.required),

      password: new FormControl('', Validators.required),
    });
  }

  login() {
    this.errorMessage = '';
    const userData: any = {
      userName: this.loginForm.get('userName')?.value,
      password: this.loginForm.get('password')?.value,
    };

    this.userService.login(userData).subscribe({
      next: (res) => {
        this.userService.loginUser(userData.userName, res.headers.get('token'));
        this.findCart();
        this.router.navigate(['homepage']);
      },
      error: (err) => {
        this.errorMessage = err;
      },
    });
  }

  findCart() {
    this.cartService.findCart().subscribe({
      next: (res) => this.cartService.updateCartCount(res.products.length),
    });
  }
}
