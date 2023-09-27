import {Component, OnInit} from '@angular/core';
import {FriendService} from "../../service/friend.service";
import {TokenStorageService} from "../../service/token-storage.service";
import {FriendModel} from "../../model/friend.model";
import {ToastrService} from "ngx-toastr";
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

  constructor(private friend: FriendService,
              private tokenService: TokenStorageService,
              private toast : ToastrService) {
    if (tokenService) {
      this.userId = this.tokenService.getUser().id
    }
  }

  ngOnInit(): void {
    this.searchForFriend()
  }

  searchForFriend() {
    this.friend.searchForFriend(this.name, this.userId, this.pageNo).subscribe(data => {
      if (data.length !== 0) {
        this.listFriend = data;
        console.log(data)
      } else {
        this.toast.warning("Không tìm thấy","Thông báo"),{
          timeOut: 3000,
          extendedTimeOut: 1500
        }
      }
    }, error => {
      console.log(error)
    })
  }

  addFriend(obj: FriendModel) {
    obj.user_id = this.userId
    this.friend.addFriend(obj).subscribe(data => {
      this.toast.success("Thêm bạn thành công","Thông báo"),{
        timeOut: 3000,
        extendedTimeOut: 1500
      }
      this.ngOnInit()
    },error => {
      this.toast.error("Không thành công vui lòng thử lại", "Thông báo"),{
        timeOut: 3000,
        extendedTimeOut: 1500
      }
    })
  }
}
