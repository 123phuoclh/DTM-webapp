import {Component, EventEmitter, OnInit, Output} from '@angular/core';
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
              private toast: ToastrService) {
    if (tokenService) {
      this.userId = this.tokenService.getUser().id
    }
  }

  ngOnInit(): void {
    this.searchForFriend()
  }

  searchForFriend() {
    let trim = this.name.trim()
    setTimeout(() => {
        this.friend.searchForFriend(trim, this.userId, this.pageNo).subscribe(data => {
          if (data.length !== 0) {
            this.listFriend = data;
          } else {
            this.listFriend = data;
            this.toast.warning("Không tìm thấy", "Thông báo", {
              timeOut: 3000,
              extendedTimeOut: 1500
            })
          }
        }, error => {
          console.error("Lỗi khi tìm kiếm bạn bè:", error);
          this.toast.error("Đã xảy ra lỗi khi tìm kiếm bạn bè, vui lòng thử lại sau.", "Thông báo", {
            timeOut: 3000,
            extendedTimeOut: 1500
          })
        })
      }, 500
    )

  }

  addFriend(obj: FriendModel) {
    obj.user_id = this.userId
    this.friend.addFriend(obj).subscribe(data => {
      this.toast.success("Thêm bạn thành công", "Thông báo", {
        timeOut: 3000,
        extendedTimeOut: 1500
      });
      this.ngOnInit()
    }, error => {
      this.toast.error("Không thành công vui lòng thử lại", "Thông báo", {
        timeOut: 3000,
        extendedTimeOut: 1500
      })
    })
  }
}
