import {Component, Input, OnInit, Output} from '@angular/core';
import {FriendService} from "../service/friend.service";
import {TokenStorageService} from "../service/token-storage.service";
import {ToastrService} from "ngx-toastr";
import {AuthService} from "../service/auth.service";
import {FriendModel} from "../model/friend.model";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {DeleteFriendComponent} from "./delete-friend/delete-friend.component";

@Component({
  selector: 'app-friendlist',
  templateUrl: './friendlist.component.html',
  styleUrls: ['./friendlist.component.scss']
})
export class FriendListComponent implements OnInit {

  deleteId!: number;
  deleteName!: string;
  listFriend: FriendModel[] = [];
  name = '';
  userID: any;
  pageNo = 0;

  constructor(private friend: FriendService,
              private token: TokenStorageService,
              private toastr: ToastrService,
              private auth: AuthService,
              private dialog: MatDialog) {
    if (token) {
      this.userID = token.getUser().id
    }
  }

  ngOnInit(): void {
    this.getListFriend()
  }

  searchFriend() {
    this.friend.searchForFriend(this.name, this.userID, this.pageNo).subscribe(data => {
      this.listFriend = data;
      console.log(data)
    }, error => {
      console.log(error)
    })
  }

  getListFriend() {
    let trim = this.name.trim()
    this.friend.getFriend(trim, this.userID, this.pageNo).subscribe(data => {
        if (data !== null) {
          this.listFriend = data;
        } else {
          this.toastr.warning("Bạn chưa có bạn bè", "Thông báo")
        }
      }
    )
  }

  deleteFriend(id?: any) {
    this.friend.deleteFriend(id).subscribe(data => {
      document.getElementById('closeModal')?.click();
      // this.event.emit(true);
    });
    this.toastr.success("Xóa thành công", "Thông báo")
  }

  openDiaLog() {
    this.dialog.open(DeleteFriendComponent, {data: this.listFriend[0].id})
  }

  delete() {
    this.ngOnInit()
  }

  logout() {
    this.auth.logout()
  }
}
