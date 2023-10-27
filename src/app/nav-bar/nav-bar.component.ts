import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    
  }

  isAdmin(): boolean {
    return this.authService.getCurrentUserRole() === 'SUPPORT_TEAM';
  }
}
