import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss',]
})

export class LoginComponent implements OnInit {
  formGroup: FormGroup;
  username: String;
  errorMessage = '';
  constructor(private formBuilder : FormBuilder,) {
  }

  ngOnInit(): void {
  }

  onSubmit() {

  }
}
