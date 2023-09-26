import {Component, OnInit} from '@angular/core';
import {FriendService} from "../../service/friend.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {FriendModel} from "../../model/friend.model";

@Component({
  selector: 'app-add-friend',
  templateUrl: './add-friend.component.html',
  styleUrls: ['./add-friend.component.scss']
})
export class AddFriendComponent implements OnInit {
  name = '';
  userId!: number;
  pageNo = 0;
  listFriend: FriendModel[] = []

  constructor(private friend: FriendService, private tokenService: TokenStorageService) {
    if (tokenService) {
      this.userId = this.tokenService.getUser().id
    }
  }

  ngOnInit(): void {
    this.searchForFriend()
  }

  searchForFriend() {
    this.friend.searchForFriend(this.name, this.userId, this.pageNo).subscribe(data => {
      this.listFriend = data;
      console.log(data)
    }, error => {
      console.log(error)
    })
  }
  addFriend(obj :FriendModel) {
    obj.user_id = this.userId
    this.friend.addFriend(obj).subscribe(data => {
      this.ngOnInit()
    })
  }
}
