import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { HomepageComponent } from './component/homepage/homepage.component';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { ReactiveFormsModule } from '@angular/forms';
import {
  HTTP_INTERCEPTORS,
  HttpClientModule,
  HttpClientXsrfModule,
} from '@angular/common/http';
import { APIList } from './constants/APIList';
import { RequestInterceptor } from './interceptor/requestInterceptor/request.interceptor';
import { UserService } from './service/user/user.service';
import { SignUpComponent } from './component/sign-up/sign-up.component';
import { ApiDetailsComponent } from './component/api-details/api-details.component';
import { OrderComponent } from './component/order/order.component';
import { CartComponent } from './component/cart/cart.component';
import { UserDashboardComponent } from './component/user-dashboard/user-dashboard.component';
import { ProductComponent } from './component/product/product.component';
import { LoadingComponent } from './component/loading/loading/loading.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomepageComponent,
    HeaderComponent,
    FooterComponent,
    SignUpComponent,
    ApiDetailsComponent,
    OrderComponent,
    CartComponent,
    UserDashboardComponent,
    ProductComponent,
    LoadingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions({
      headerName: 'token',
    }),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestInterceptor,
      multi: true,
    },
    APIList,
    UserService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
