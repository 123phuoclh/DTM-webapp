import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";
import {TokenStorageService} from "../service/token-storage.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss',]
})

export class LoginComponent implements OnInit {
  // @ts-ignore
  formGroup: FormGroup;
  errorMessage = '';
  isLogin = false;

  constructor(private formBuild: FormBuilder,
              private toast: ToastrService,
              private authService: AuthService,
              private router: Router,
              private tokenStorage : TokenStorageService) {
  }

  ngOnInit(): void {
    this.formGroup = this.formBuild.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    if(this.tokenStorage.getToken()){
      this.router.navigate(['/dashboard/User']);
    }
  }

  onSubmit() {
    this.authService.login(this.formGroup.value).subscribe(data => {
        window.localStorage.setItem('token', data);
        console.log(data)
        this.router.navigate(['/dashboard/User'])
        this.authService.isLoggedIn = true;
        this.formGroup.reset();
      },
      (err) => {
        this.errorMessage = err.error.message;
        this.authService.isLoggedIn = false;
        this.toast.error("Sai tên đăng nhập hoặc mật khẩu hoặc tài khoản chưa được kích hoạt", "Đăng nhập thất bại: ", {
          timeOut: 3000,
          extendedTimeOut: 1500
        });
      });
  }
}
