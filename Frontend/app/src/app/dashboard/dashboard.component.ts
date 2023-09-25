import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {TokenStorageService} from "../service/token-storage.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {UserService} from "../service/user.service";
import {AuthService} from "../service/auth.service";

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

    public name = '';
    public address = '';
    public nickName = '';
    public email = '';
    public phoneNumber = '';
    private id: undefined;
    private username = '';
    public formGroup: FormGroup | any
    public isSubmitted = false;
    data = this.tokenStorage.getUser()

    constructor(private tokenStorage: TokenStorageService,
                private router: Router,
                private toastr: ToastrService,
                private user: UserService,
                private formBuild: FormBuilder,
                private auth: AuthService) {
        if (this.data) {
            this.name = this.data.name
            this.address = this.data.address
            this.nickName = this.data.nickName
            this.email = this.data.email
            this.phoneNumber = this.data.phoneNumber
            this.id = this.data.id
            this.username = this.data.username
        } else {
            this.router.navigateByUrl('/login')
        }
    }

    validation_messages = {
        'nickName': [
            {type: 'required', message: 'Trường này không được để trống!'},
        ],
        'name': [
            {type: 'required', message: 'Trường này không được để trống!'},
        ],
        'email': [
            {type: 'required', message: 'Trường này không được để trống!'},
            {type: 'pattern', message: 'Email sai định dạng'}
        ]
    }

    ngOnInit(): void {
        this.formGroup = this.formBuild.group({
            email: ['', [Validators.required, Validators.pattern('^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$')]],
            name: ['', Validators.required],
            nickName: ['', Validators.required],
            address: '',
            phoneNumber: '',
            avatar: '',
            id: this.id,
            username: this.username
        })
    }

    onSubmit() {
        if (this.formGroup.invalid) {
            this.toastr.warning("Form phải được điền đúng định dạng", "Warning:", {
                positionClass: 'toast-bottom-right',
                timeOut: 1500,
                extendedTimeOut: 1500
            })
        } else {
            this.user.edit(this.formGroup.value).subscribe(data => {
                    this.isSubmitted = true;
                    this.tokenStorage.signOut();
                    window.location.reload()
                },
                err => {
                    this.toastr.error(err.error.message, 'Lỗi: ', {
                        positionClass: 'toast-bottom-right',
                        timeOut: 1500,
                        extendedTimeOut: 1500
                    });
                    this.isSubmitted = false;
                })
        }
    }

    logout() {
        this.auth.logout()
    }
}
