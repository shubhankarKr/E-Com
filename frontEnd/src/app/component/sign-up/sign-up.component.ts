import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})
export class SignUpComponent implements OnInit {
  signUpForm!: FormGroup;
  errorMessage!: string;

  constructor(private fb: FormBuilder, private userService: UserService) {}
  ngOnInit(): void {
    this.signUpForm = this.fb.group({
      userName: ['', Validators.required],
      password: ['', Validators.required],
      email: [],
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
      },
      error: (err) => (this.errorMessage = err),
    });
  }
}
