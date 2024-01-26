export class APIList {
  //server host
  // HOST_URL = 'https://ecom-myu7.onrender.com' + '/ecom';
  // local host
  HOST_URL = 'http://localhost:8080' + '/ecom';

  USER_BASE_URL = this.HOST_URL + '/user';
  PRODUCT_BASE_URL = this.HOST_URL + '/products';
  METADATA_BASE_URL = this.HOST_URL + '/metadata';

  LOGIN_API = this.USER_BASE_URL + '/authenticate';
  REGISTER_API = this.USER_BASE_URL + '/register';

  GET_ALL_PRODUCTS = this.PRODUCT_BASE_URL + '/getAllProducts';
  GET_CATEGORY = this.METADATA_BASE_URL + '/getAllCategory';
}
