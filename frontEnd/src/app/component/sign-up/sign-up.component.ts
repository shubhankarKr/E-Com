import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})
export class SignUpComponent implements OnInit {
  signUpForm!: FormGroup;
  errorMessage!: string;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.signUpForm = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required],
      email: ['', Validators.email],
    });
  }

  signUp() {
    if (this.errorMessage) {
      this.errorMessage = '';
    }
    const UserData = {
      userName: this.signUpForm.get('userName')?.value,
      password: this.signUpForm.get('password')?.value,
      email: this.signUpForm.get('email')?.value,
    };
    this.userService.signUp(UserData).subscribe({
      next: (res) => {
        console.log(' signUp res ' + res);
        this.userService.login(UserData).subscribe({
          next: (res) => {
            console.log(' login response ' + res);
            this.userService.loginUser(
              UserData.userName,
              res.headers.get('token')
            );
            this.router.navigate(['homepage']);
          },
          error: (err) => console.log(' login erro ' + err),
        });
      },
      error: (err) => (this.errorMessage = err),
    });
  }
}
