import { Component, OnInit } from '@angular/core';
import { Observable, Observer } from 'rxjs';
import { ProductService } from 'src/app/service/product/product.service';
import { UserService } from 'src/app/service/user/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  showLogin!: boolean;
  currentUser!: string | null;
  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.userService.getLoginFlag().subscribe({
      next: (res) => {
        // console.log(' home component subscribe ' + res);
        this.showLogin = res;
        this.currentUser = this.userService.getCurrentUser();
      },
    });
  }

  logOut() {
    this.userService.logoutUser();
  }
}
