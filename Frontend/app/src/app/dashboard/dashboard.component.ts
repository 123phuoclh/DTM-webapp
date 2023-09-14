import {Component, OnInit} from '@angular/core';
import {FormGroup} from "@angular/forms";
import {Subject} from "rxjs";
import {Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit{

  public name = '';
  public address ='';
  public nickName = '';
  public email = '';
  public phoneNumber = '';
  private id: undefined

  constructor(private router: Router) {
  }
  ngOnInit(): void {

  }

}
