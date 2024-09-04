import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { LoggedoutComponent } from './loggedout/loggedout.component';
import { LoggedinComponent } from './loggedin/loggedin.component';
import { authGuard } from './auth.guard';

export const routes: Routes = [
    {path: "", component: HomeComponent},
    {path: "login", component: LoginComponent},
    {path: "signup", component: SignupComponent},
    {path: "loggedin", component: LoggedinComponent, canActivate: [authGuard]},
    {path: "loggedout", component: LoggedoutComponent}
];
