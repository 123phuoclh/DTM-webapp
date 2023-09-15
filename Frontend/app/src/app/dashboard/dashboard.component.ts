import {Component, OnInit} from '@angular/core';0
import {Router} from "@angular/router";
import {TokenStorageService} from "../service/token-storage.service";
import {FormGroup} from "@angular/forms";

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
  data = this.tokenStorage.getUser()
  public formGroup : FormGroup | any
  constructor(private tokenStorage: TokenStorageService,
              private router: Router) {
    if (this.data) {
      this.name = this.data.name
      this.address = this.data.address
      this.nickName = this.data.nickName
      this.email = this.data.email
      this.phoneNumber = this.data.phoneNumber
    } else {
      this.router.navigateByUrl('/login')
    }
  }
  ngOnInit(): void {
  }

  onSubmit() {

  }
  scrollToElement(elementId : any) {
    const element = document.getElementById(elementId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  }
}
