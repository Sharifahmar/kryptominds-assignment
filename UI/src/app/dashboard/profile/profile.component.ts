import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserProfileService } from '../service/user-profile.service';
import { Router, ActivatedRoute } from '@angular/router';
import * as alertFunctions from '../../shared/data/sweet-alerts';
import { ProfileService } from '../service/profile.service';
import { ProfileModel } from '../helper/model/profile-model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  constructor(private profileService: ProfileService, private router: Router) { }
  profileForm: FormGroup;
  cities = ['Amravati','Pune'];
  states = ['Maharashtra'];
  country = ['India'];
  donarModel:ProfileModel=new ProfileModel();

  ngOnInit() {
    this.profileForm = new FormGroup({
      'firstName': new FormControl("", [Validators.required]),
      'lastName': new FormControl("", [Validators.required]),
      'email': new FormControl("", [Validators.required, Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]),
      'phoneNumber': new FormControl("", [Validators.required, Validators.pattern("^[0-9]{10}$")]),
      'address': new FormControl("", [Validators.required]),
      'zipCode': new FormControl("", [Validators.required]),
      'city': new FormControl("", [Validators.required]),
      'state': new FormControl("", [Validators.required]),
      'country': new FormControl("", [Validators.required]),
      'cName': new FormControl("", [Validators.required]),
      'cEmail': new FormControl("", [Validators.required,Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]),
      'cWebsite': new FormControl("", [Validators.required]),
      'cPhoneNumber': new FormControl("", [Validators.required,Validators.pattern("^[0-9]{10}$")]),
      'cAddress': new FormControl("", [Validators.required]),
      'cZipCode': new FormControl("", [Validators.required]),
      'cCity': new FormControl("", [Validators.required]),
      'cState': new FormControl("", [Validators.required]),
      'cCountry': new FormControl("", [Validators.required]),
      'status': new FormControl(true)
    });
  }


  cancelProfile(): void {
    this.profileForm.reset();
    this.router.navigate(['/DashBoardGrid']);
  }

  registerProfile(): void {
      this.profileService.registerProfile(this.profileForm.value).subscribe(
        response => {
          alertFunctions.customtypeSuccess("Congratulations.!!", "Profile Register Successfully..!!")
          this.profileForm.reset();
        },
        error => {
          alertFunctions.custometypeError("Oops.!!", "Something went wrong..")
        });
    
  }


  get email() { return this.profileForm.get('email'); }
  get phoneNumber() { return this.profileForm.get('phoneNumber'); }


}
