import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FriendService} from "../../service/friend.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-delete-friend',
  templateUrl: './delete-friend.component.html',
  styleUrls: ['./delete-friend.component.scss']
})
export class DeleteFriendComponent implements OnInit {

  constructor(private friend: FriendService,
              private toast: ToastrService) {
  }

  @Input()
  friendId!: number;

  @Input()
  name = '';

  @Output()
  event = new EventEmitter<boolean>;

  ngOnInit(): void {

  }


  deleteFriend() {
    console.log(this.friendId)
    this.friend.deleteFriend(this.friendId).subscribe(data => {
      document.getElementById('closeModal')?.click();
      this.event.emit(true);
    });
    this.toast.success("Xóa thành công", "Thông báo")
  }
}
