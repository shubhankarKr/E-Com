import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css'],
})
export class MyProfileComponent implements OnInit {
  userData!: any;
  constructor(private userService: UserService) {}

  ngOnInit(): void {
    let currentUserName: string | null = this.userService.getCurrentUser();
    if (currentUserName != null) {
      this.findByUserNameOrEmail(currentUserName);
    }
  }

  findByUserNameOrEmail(value: string) {
    this.userService.findByUserNameOrEmail(value).subscribe({
      next: (res) => (this.userData = res),
    });
  }
}
