import { Component, OnInit } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  logOut() {
    this.userService.logoutUser();
  }
  showLogin!: boolean;
  ngOnInit(): void {
    this.userService.getLoginFlag().subscribe({
      next: (res) => {
        // console.log(' home component subscribe ');
        this.showLogin = res;
      },
    });
  }
  constructor(private userService: UserService) {}
}
