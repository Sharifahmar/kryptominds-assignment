import { Component, ViewChild, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from "@angular/router";
import { LoginAuthService } from '../service/login-auth.service';
import { LoginModel } from '../../../helper/model/login-model';
import { UserProfileService } from '../../../dashboard/service/user-profile.service';
@Component({
    selector: 'app-login-page',
    templateUrl: './login-page.component.html',
    styleUrls: ['./login-page.component.scss']
})

export class LoginPageComponent implements OnInit {
    loginForm: FormGroup;
    loginModel: LoginModel = new LoginModel();
    loading: boolean = false;
    error:boolean=false;
    constructor(private router: Router,
        private route: ActivatedRoute, private loginAuthService: LoginAuthService,private userProfileService:UserProfileService) { }

    ngOnInit() {
        this.loginForm = new FormGroup({
            'email': new FormControl("", [Validators.required, Validators.pattern("^[a-zA-Z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$")]),
            'password': new FormControl("", [Validators.required]),
        });

        sessionStorage.clear();
    }
    login(): void {
        this.loading = true;
        this.loginModel.setEmail(this.email.value);
        this.loginModel.setPassword(this.password.value);
        this.loginAuthService.getToken(this.loginModel).subscribe(response => {
            this.loading = false;
            sessionStorage.setItem('token', response.accessToken);
            this.userProfileService.userDetails({"status":true}).subscribe(resp=>{
               if(resp.roles[0].roleName==='ROLE_USER'){
                this.router.navigate(['/AddProfile']);
               }else{
                this.router.navigate(['/DashBoardGrid']);
               }

            })
           
        },
            error => {
                this.loading = false;
                this.error=true;
            });

    }

    // On registration link click
    onRegister() {
        sessionStorage.removeItem('token');
        this.router.navigate(['/pages/register']);

    }

    get email() { return this.loginForm.get('email'); }

    get password() { return this.loginForm.get('password'); }
}