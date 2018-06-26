import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AuthService} from './shared/auth/auth.service';
import {HttpClientModule} from '@angular/common/http';

import {AppComponent} from './app.component';
import {UserListComponent} from './user-list/user-list.component';


@NgModule({
  declarations: [
    AppComponent,
    UserListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
