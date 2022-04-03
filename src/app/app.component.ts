import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Crud operation';
  title_two= 'Product'
  title_login = 'Login'
 
  constructor(private routes: Router){}

  get isAuthenticated() : boolean {
    return AuthService.isAuthenticated();
  }

  logout() : void {
    AuthService.Logout();
    this.routes.navigate(['/login']);
  }
}
