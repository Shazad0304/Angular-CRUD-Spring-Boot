import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { User } from '../User';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  user: User = new User();

  constructor(private authService: AuthService,
    private router: Router) { }

  ngOnInit(): void {
    if (localStorage.getItem('session') != null) {
      this.router.navigate(['product']);
    }
  }
  

  save() {
    this.authService.login(this.user)
      .subscribe(
        data => {
          localStorage.setItem('session', data.token);
          this.router.navigate(['product']);
          console.log(data);
          
        },
        error => {
          alert("Invalid Credentials");
        });
    this.user = new User();
  }
 
  onSubmit() {
    this.save();
  }

}
