import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/api/user';

  constructor(private http: HttpClient) { }


  login(user: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, user);
  }

  static getToken() : String{
      return localStorage.getItem('session');
  }

  static isAuthenticated() : boolean{
    if (localStorage.getItem('session') != null) {
      return true;
    }
    else {
      return false;
    }
  }

  static Logout() : void {
    localStorage.removeItem('session');

  }


}
