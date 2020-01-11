import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ContentPagesRoutingModule } from "./content-pages-routing.module";
import { ErrorPageComponent } from "./error/error-page.component";
import { LoginPageComponent } from "./login/login-page.component";
import { RegisterPageComponent } from "./register/register-page.component";
import { HttpClientModule } from '@angular/common/http';
import { NgxLoadingModule } from 'ngx-loading';


@NgModule({
    imports: [
        CommonModule,
        ContentPagesRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        NgxLoadingModule.forRoot({
            primaryColour: '#ffffff',
            backdropBorderRadius: '3px'
        })
    ],
    declarations: [
        ErrorPageComponent,
        LoginPageComponent,
        RegisterPageComponent

    ]
})
export class ContentPagesModule { }
