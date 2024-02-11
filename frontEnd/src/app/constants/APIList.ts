export class APIList {
  //server host
  // HOST_URL = 'https://ecom-myu7.onrender.com' + '/ecom';
  // HOST_URL = 'https://ecom-myu7.onrender.com' + '/ecom';
  // local host
  HOST_URL = 'http://localhost:8080' + '/ecom';

  USER_BASE_URL = this.HOST_URL + '/user';
  PRODUCT_BASE_URL = this.HOST_URL + '/products';
  METADATA_BASE_URL = this.HOST_URL + '/metadata';
  CART_BASE_URL = this.HOST_URL + '/cart';

  LOGIN_AUTHENTICATE = this.USER_BASE_URL + '/authenticate';
  LOGIN_REGISTER = this.USER_BASE_URL + '/register';
  FIND_USER_BY_USERNAME_OR_EMAIL =
    this.USER_BASE_URL + '/findByUserNameOrEmail/{value}';

  PRODUCTS_FIND_ALL = this.PRODUCT_BASE_URL + '/findAll';
  PRODUCTS_ADD = this.PRODUCT_BASE_URL + '/create';

  CATEGORY_FIND_ALL = this.METADATA_BASE_URL + '/findAll';

  API_DETAILS_SAVE = this.METADATA_BASE_URL + '/apiDetails/create';
  API_DETAILS_FIND_AL = this.METADATA_BASE_URL + '/apiDetails/findAll';
  API_DETAILS_UPDATE = this.METADATA_BASE_URL + '/apiDetails/update';
  API_DETAILS_DELETE_BY_ID =
    this.METADATA_BASE_URL + '/apiDetails/delete/id/{id}';
  METADATA_FIND_ALL_CATEGORIES_MD =
    this.METADATA_BASE_URL + '/findAllCategoriesMD';

  CART_FIND = this.CART_BASE_URL + '/find';
  CART_UPDATE = this.CART_BASE_URL + '/update';
  CART_ADD = this.CART_BASE_URL + '/add';
  CART_REMOVE = this.CART_BASE_URL + '/remove';
}
