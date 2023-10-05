import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TokenStorageService} from "../service/token-storage.service";
import {ToastrService} from "ngx-toastr";
import {UserService} from "../service/user.service";
import {AuthService} from "../service/auth.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {UserModel} from "../model/user.model";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  public proFile: UserModel
  data : any;
  role = '';

  constructor(private tokenStorage: TokenStorageService,
              private router: Router,
              private toastr: ToastrService,
              private user: UserService,
              private auth: AuthService,
              private modalService: NgbModal) {
    this.proFile = new UserModel()
    if (this.tokenStorage.getUser()) {
      this.data = this.tokenStorage.getUser().user
      this.proFile.name = this.data.name
      this.proFile.address = this.data.address
      this.proFile.nickName = this.data.nickName
      this.proFile.email = this.data.email
      this.proFile.phoneNumber = this.data.phoneNumber
      this.proFile.id = this.data.id
      this.proFile.username = this.data.username
    } else {
      this.router.navigate(['/login'])
    }
  }

  ngOnInit(): void {
    this.data = this.tokenStorage.getUser().user;
    this.role = this.tokenStorage.getUser().role[0];
  }

  onSubmit() {
    if (this.proFile.email.trim() === '') {
      this.toastr.warning("Email không đúng định dạng hoặc để trống", "Warning:", {
        positionClass: 'toast-bottom-right',
        timeOut: 1500,
        extendedTimeOut: 1500
      })
    } else if (this.proFile.name.trim() === '') {
      this.toastr.warning("Tên không được để trống", "Warning:", {
        positionClass: 'toast-bottom-right',
        timeOut: 1500,
        extendedTimeOut: 1500
      })
    } else {
      this.user.edit(this.proFile).subscribe(res => {
          if (res.message !== "Email đã tồn tại") {
            this.tokenStorage.saveUserLocal(this.proFile)
            this.toastr.success("Thay đổi thành công", "Thông báo")
            this.ngOnInit()
          } else {
            this.toastr.warning(res.message, "Thông báo")
          }
        },
        err => {
          this.toastr.error(err.error.message, 'Lỗi: ', {
            positionClass: 'toast-bottom-right',
            timeOut: 1500,
            extendedTimeOut: 1500
          });
        })
    }
  }

  openModalEdit(content: any) {
    this.modalService.open(content, {backdrop: 'static', ariaLabelledBy: 'modal-basic-title'}).result.then(
      (result) => {
        if (result === 'cancel') {
          this.proFile = this.data
          this.ngOnInit()
        }
      })
  }

  logout() {
    this.auth.logout()
  }
}
