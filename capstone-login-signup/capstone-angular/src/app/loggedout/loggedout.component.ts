import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-loggedout',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  templateUrl: './loggedout.component.html',
  styleUrl: './loggedout.component.css'
})
export class LoggedoutComponent {

}
