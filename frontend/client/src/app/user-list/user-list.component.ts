import {Component, OnInit} from '@angular/core';
import {AuthService} from "../shared/auth/auth.service";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: Array<any>;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.authService.getAllUsers().subscribe(data => {
      this.users = data;
    });
  }
}
