import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {AuthService} from "../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  // @ts-ignore
  formGroup:FormGroup;
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  isSubmitted = false;
constructor(private formBuild: FormBuilder,
            private toastr: ToastrService,
            private authService: AuthService,
            private router : Router) {
}

  ngOnInit(): void {
    this.formGroup = this.formBuild.group({
        password: ['',[Validators.required,Validators.minLength(8),Validators.maxLength(32)]],
        email:['',[Validators.required,Validators.pattern('^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$')]],
        name:['',Validators.required],
        username:['',Validators.required]
      }
    );
    }
  validation_messages = {
    'password':[
      {type: 'required',message: 'Trường này không được để trống!'},
      {type: 'minlength', message: 'Mật khẩu nhiều hơn 8 ký tự'},
      {type: 'maxlength', message: 'Mật khẩu ít hơn 32 ký tự'},
    ],
    'username':[
      {type: 'required',message: 'Trường này không được để trống!'},
    ],
    'name':[
      {type: 'required',message: 'Trường này không được để trống!'},
    ],
    'email':[
      {type: 'required',message: 'Trường này không được để trống!'},
      {type:'pattern',message: 'Email sai định dạng' }
    ]}
  onSubmit() {
    if(this.formGroup.invalid){
      this.toastr.warning("Form phải được điền đúng định dạng","Warning:",{
        positionClass:'toast-bottom-right',
        timeOut: 1500,
        extendedTimeOut:1500
      })
    }else{
      this.isSubmitted=true;
      this.authService.register(this.formGroup.value).subscribe(
        data => {
          this.isSuccessful = true;
          this.isSignUpFailed = false;
          setTimeout(() => {
            this.router.navigateByUrl("/login");
          }, 10000);

        },
        err => {
          this.toastr.error(err.error.message,'Lỗi: ',{
            positionClass:'toast-bottom-right',
            timeOut: 1500,
            extendedTimeOut:1500
          });
          this.errorMessage = err.error.message;
          this.isSignUpFailed = true;
          this.isSubmitted=false;
        }
      );
    }
  }
}
