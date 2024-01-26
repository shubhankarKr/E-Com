import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  errorMessage!: string;

  constructor(private userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      userName: new FormControl('', Validators.required),

      password: new FormControl('', Validators.required),
    });
  }

  login() {
    this.errorMessage = '';
    let user: User = new User(
      this.loginForm.get('userName')?.value,
      this.loginForm.get('password')?.value
    );

    this.userService.login(user).subscribe({
      next: (res) => {
        user.setAuth(true);
        user.setPasswordEmpty();
        sessionStorage.setItem('token', res.headers.get('token'));
        this.userService.loginUser();
        this.router.navigate(['homepage']);
      },
      error: (err) => {
        this.errorMessage = err;
      },
    });
  }
}
