import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { RegistrationService } from '../service/registeration.service';
import { LoginAuthService } from '../service/login-auth.service';
import * as alertFunctions from '../../../shared/data/sweet-alerts';
import { Router } from '@angular/router';

@Component({
    selector: 'app-register-page',
    templateUrl: './register-page.component.html',
    styleUrls: ['./register-page.component.scss']
})

export class RegisterPageComponent implements OnInit {
    registerForm: FormGroup;
    loading:boolean=false;
    roles = ['ROLE_USER','ROLE_ADMIN'];
    constructor(private registrationService: RegistrationService,private loginAuthService:LoginAuthService,private router:Router) { }

    ngOnInit() {
        this.registerForm = new FormGroup({
            'firstName': new FormControl("", [Validators.required]),
            'lastName': new FormControl("", [Validators.required]),
            'email': new FormControl("", [Validators.required, Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]),
            'phoneNumber': new FormControl("", [Validators.required, Validators.pattern("^[0-9]{10}$")]),
            'password': new FormControl("", [Validators.required,Validators.minLength(4)]),
            'role': new FormControl("", [Validators.required]),
        }, { updateOn: 'blur' });

        sessionStorage.clear();
   
    }
    register(): void {
        this.loading=true;
        this.registrationService.registerUser(this.registerForm.value).subscribe(
            response => {
                this.loading=false;
                response.status===true ? alertFunctions.customtypeSuccess("Congratulations.!!","User Registeration Done") : alertFunctions.custometypeError("Oops.!!","Something went wrong")
                this.registerForm.reset();
                this.router.navigate(['pages/login']);      
            },
            error => {
                this.loading=false;
                alertFunctions.custometypeError("Oops.!!", "Something went wrong..")

            }
        )}

    get email() { return this.registerForm.get('email'); }
    get phoneNumber() { return this.registerForm.get('phoneNumber'); }
    get password() { return this.registerForm.get('password'); }

}