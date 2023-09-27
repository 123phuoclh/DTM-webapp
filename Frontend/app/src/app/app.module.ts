import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ToastrModule } from 'ngx-toastr';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './register/register.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DashboardComponent } from './dashboard/dashboard.component';
import {MatTabsModule} from "@angular/material/tabs";
import {InterceptorProvider} from "./helper/Interceptor";
import {HttpClientModule} from "@angular/common/http";
import {NgOptimizedImage} from "@angular/common";
import {FriendListComponent} from "./friendlist/friendlist.component";
import {MatDialogModule} from "@angular/material/dialog";
import { AddFriendComponent } from './friendlist/add-friend/add-friend.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    DashboardComponent,
    FriendListComponent,
    AddFriendComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        ToastrModule.forRoot(),
        ReactiveFormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatTabsModule,
        NgOptimizedImage,
        FormsModule,
        MatDialogModule,
        NgbModule
    ],
  providers: [InterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
