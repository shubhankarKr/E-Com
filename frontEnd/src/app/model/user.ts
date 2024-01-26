export class User {
  userName!: string;
  private password!: string;
  private auth: boolean = false;

  constructor(userName: string, password: string, auth?: boolean) {
    this.userName = userName;
    this.password = password;
    if (auth) {
      this.auth = auth;
    }
  }

  setAuth(auth: boolean) {
    this.auth = auth;
  }

  setPasswordEmpty() {
    this.password = '';
  }

  getPassword(){
    return this.password;
  }
}
