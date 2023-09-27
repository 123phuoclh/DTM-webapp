import {Component, OnInit} from '@angular/core';
import {FriendService} from "../service/friend.service";
import {TokenStorageService} from "../service/token-storage.service";
import {ToastrService} from "ngx-toastr";
import {AuthService} from "../service/auth.service";
import {FriendModel} from "../model/friend.model";
import {MatDialog} from "@angular/material/dialog";
import {AddFriendComponent} from "./add-friend/add-friend.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

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
    totalPage!: number

    constructor(private friend: FriendService,
                private token: TokenStorageService,
                private toastr: ToastrService,
                private auth: AuthService,
                private dialog: MatDialog,
                private modalService: NgbModal) {
        if (token) {
            this.userID = token.getUser().id
        }
    }

    ngOnInit(): void {
        this.getListFriend(0)
    }

    openDeleteModal(id: number, content: any) {
        this.deleteId = id;
        this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(
            (result) => {
                if (result === 'delete') {
                    this.friend.deleteFriend(this.deleteId).subscribe(data => {
                            this.toastr.success("Xóa thành công", "Thông báo"), {
                                timeOut: 3000,
                                extendedTimeOut: 1500
                            }
                            this.ngOnInit()
                        }
                    )
                }
            },
        );
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
            }
        )
    }

    openDiaLog() {
        this.dialog.open(AddFriendComponent)
    }

    logout() {
        this.auth.logout()
    }
}
