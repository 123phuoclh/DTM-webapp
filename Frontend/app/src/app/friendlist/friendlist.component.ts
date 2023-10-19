import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FriendService} from "../service/friend.service";
import {TokenStorageService} from "../service/token-storage.service";
import {ToastrService} from "ngx-toastr";
import {AuthService} from "../service/auth.service";
import {FriendModel} from "../model/friend.model";
import {MatDialog} from "@angular/material/dialog";
import {AddFriendComponent} from "./add-friend/add-friend.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";

@Component({
  selector: 'app-friendlist',
  templateUrl: './friendlist.component.html',
  styleUrls: ['./friendlist.component.scss']
})
export class FriendListComponent implements OnInit {
  @Output()
  isPermitted = new EventEmitter<boolean>;

  deleteId!: number;
  deleteName!: string;
  listFriend: FriendModel[] = [];
  name = '';
  userID: any;
  pageNo = 0;
  totalPage!: number;
  selectedFriend!: FriendModel | any;
  role = ''

  constructor(private friend: FriendService,
              private token: TokenStorageService,
              private route: Router,
              private toastr: ToastrService,
              private auth: AuthService,
              private dialog: MatDialog,
              private modalService: NgbModal) {
    if (this.token.getUser()) {
      this.userID = this.token.getUser().id;
      this.role = this.token.getUser().role[0]
      if (this.role !== 'ADMIN') {
        this.route.navigate(['/dashboard/user'])
      }
    } else {
      this.route.navigate(['/login'])
    }

  }

  ngOnInit(): void {
    if (this.pageNo + 1 === this.totalPage && this.pageNo !== 0) {
      this.pageNo -= 1;
      this.getListFriend(this.pageNo);
    } else {
      this.getListFriend(this.pageNo)
    }
    this.selectedFriend = {};
  }

  openDeleteModal(id: number, content: any) {
    this.deleteId = id;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(
      (result) => {
        if (result === 'delete') {
          this.friend.deleteFriend(this.deleteId).subscribe(data => {
              this.toastr.success("Xóa thành công", "Thông báo", {
                timeOut: 3000,
                extendedTimeOut: 1500
              })
              this.ngOnInit()
            }, error => {
              this.toastr.error("Xóa thất bại", "Thông báo")
            }
          )
        }
        if (result === 'cancel') {
          this.modalService.dismissAll()
        }
      },
    );
  }

  openEditModal(id: number, content: any) {
    const value = this.listFriend.find(value => value.id === id);
    if (value) {
      this.selectedFriend = value;
    }
    this.modalService.open(content, {windowClass: 'width-80'}).result.then((result) => {
      if (result === 'edit') {
        this.friend.editFriend(this.selectedFriend).subscribe(res => {
          this.toastr.success("Sửa thành công", "Thông báo", {
            timeOut: 3000,
            extendedTimeOut: 1500
          })
          this.ngOnInit()
        }, error => {
          this.toastr.error("Lỗi hệ thống vui lòng thử lại", " Thông báo")
        })
      } else {
        this.ngOnInit();
      }
    })
  }

  getListFriend(pageNo: number) {
    let trim = this.name.trim()
    this.friend.getFriend(trim, this.userID, pageNo).subscribe(data => {
        if (data !== null) {
          this.listFriend = data.content;
          this.totalPage = data.totalPages;
        } else {
          this.toastr.warning("Bạn chưa có bạn bè", "Thông báo"), {
            timeOut: 3000,
            extendedTimeOut: 1500
          }
        }
      }, error => {
      this.isPermitted.emit(false);
      this.route.navigate(['/dashboard/user'])
      }
    )
  }

  openDiaLog() {
    this.dialog.open(AddFriendComponent);
    this.dialog.afterAllClosed.subscribe(next => {
        this.ngOnInit()
      }
    )
  }

  logout() {
    this.auth.logout()
  }
}
