import { Component, OnInit, AfterViewInit, OnChanges } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { LoginAuthService } from '../service/login-auth.service';
import { UserProfileService } from '../service/user-profile.service';
import { Router } from '@angular/router';
import * as alertFunctions from '../shared/data/sweet-alerts';
import { ComponentInteractionService } from '../shared/behavior-subject-service/component-interaction.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit{

  constructor(private loginAuthService: LoginAuthService, private componentInteractionService: ComponentInteractionService, private router: Router, private userProfileService: UserProfileService) { }
  userProfileForm: FormGroup;
  cities = ['Amravati','Pune'];
  states = ['Maharashtra'];
  country = ['India'];

  userProfileRecieve: any = {};

  ngOnInit() {
    this.userProfileForm = new FormGroup({
      'firstName': new FormControl("", [Validators.required]),
      'lastName': new FormControl("", [Validators.required]),
      'email': new FormControl("", [Validators.required, Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]),
      'phoneNumber': new FormControl("", [Validators.required, Validators.pattern("^[0-9]{10}$")]),
      'password': new FormControl("", [Validators.required, Validators.minLength(4)]),
      'address': new FormControl("", [Validators.required]),
      'zipCode': new FormControl("", [Validators.required]),
      'city': new FormControl("", [Validators.required]),
      'state': new FormControl("", [Validators.required]),
      'country': new FormControl("", [Validators.required]),
    });
   
    this.loadUserProfileDetails();

  }

  updateUser(): void {
    this.userProfileService.updateUserDetails(this.userProfileForm.getRawValue()).subscribe(
      response => {
        this.userProfileForm.setValue(response);  
        alertFunctions.customtypeSuccess("Congratulations.!!", "User Update Successfully..!!")
      },
      error => {
        alertFunctions.custometypeError("Oops.!!", "Something went wrong..")
      });
  }

  cancelUser(): void {
    this.userProfileForm.reset();
    this.router.navigate(['/DashBoardGrid']);
  }

  loadUserProfileDetails():void{
    this.userProfileForm.controls['email'].disable();
    this.userProfileForm.controls['phoneNumber'].disable();
    this.componentInteractionService.currentMessage.subscribe(responseObj => {
      this.userProfileRecieve = responseObj
      this.userProfileForm.patchValue(this.userProfileRecieve);
    });

  
  }


  get email() { return this.userProfileForm.get('email'); }
  get phoneNumber() { return this.userProfileForm.get('phoneNumber'); }


}
