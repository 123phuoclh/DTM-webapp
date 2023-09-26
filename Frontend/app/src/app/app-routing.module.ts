import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {RegisterComponent} from "./register/register.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {FriendListComponent} from "./friendlist/friendlist.component";
import {DeleteFriendComponent} from "./friendlist/delete-friend/delete-friend.component";
import {AddFriendComponent} from "./friendlist/add-friend/add-friend.component";

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {path: 'add', component: AddFriendComponent},
  {
    path: 'friend', component: FriendListComponent, children:
      [{path: 'delete', component: DeleteFriendComponent}]
  },
  {path: 'dashboard/user', component: DashboardComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
