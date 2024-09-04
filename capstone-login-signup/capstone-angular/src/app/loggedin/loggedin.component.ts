import { Component, inject } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { SecurityService } from '../security.service';
import { UserRegister } from '../UserRegister';

@Component({
  selector: 'app-loggedin',
  standalone: true,
  imports: [RouterLink, RouterOutlet],
  templateUrl: './loggedin.component.html',
  styleUrl: './loggedin.component.css'
})
export class LoggedinComponent {

  constructor(private securityService: SecurityService){};

  //securityService: SecurityService = inject(SecurityService);

  currentUser: UserRegister = new UserRegister(0,"","","");

  displayUser(){
    console.log(this.securityService.currentUser);
    this.currentUser = this.securityService.currentUser;
  }

  signOut(){
    this.securityService.signOut();
  }
}
