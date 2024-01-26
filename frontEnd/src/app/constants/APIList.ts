export class APIList {
  //server host
  HOST_URL = 'https://ecom-myu7.onrender.com' + '/ecom';
  // local host
  //   HOST_URL = 'http://localhost:8080' + '/ecom';

  USER_HOST = this.HOST_URL + '/user';

  LOGIN_API = this.USER_HOST + '/authenticate';
}
