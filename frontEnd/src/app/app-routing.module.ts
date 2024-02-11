import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './component/homepage/homepage.component';
import { LoginComponent } from './component/login/login.component';
import { SignUpComponent } from './component/sign-up/sign-up.component';
import { ApiDetailsComponent } from './component/api-details/api-details.component';
import { UserDashboardComponent } from './component/user-dashboard/user-dashboard.component';
import { OrderComponent } from './component/order/order.component';
import { CartComponent } from './component/cart/cart.component';
import { ProductComponent } from './component/product/product.component';
import { MyProfileComponent } from './component/my-profile/my-profile.component';
import { AddProductComponent } from './component/add-product/add-product.component';

const routes: Routes = [
  { path: 'homepage', component: HomepageComponent },
  { path: '', redirectTo: 'homepage', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'signUp', component: SignUpComponent },
  { path: 'user/dashboard', component: UserDashboardComponent },
  { path: 'user/order', component: OrderComponent },
  { path: 'user/profile', component: MyProfileComponent },
  { path: 'user/cart', component: CartComponent },
  { path: 'api', component: ApiDetailsComponent },
  { path: 'product/:id', component: ProductComponent },
  { path: 'products/add', component: AddProductComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
