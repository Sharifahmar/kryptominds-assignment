import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";
import { DashboardRoutingModule } from "./dashboard-routing.module";
import { ChartistModule } from 'ng-chartist';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MatchHeightModule } from "../shared/directives/match-height.directive";
import { NgxDatatableModule } from '@swimlane/ngx-datatable';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { DTFilterComponent } from './data-tables-donar/dt-filter.component';
import { NgxLoadingModule } from 'ngx-loading';
import { NgSelectModule } from '@ng-select/ng-select';
import { ProfileComponent } from './profile/profile.component';

@NgModule({
    imports: [
        CommonModule,
        DashboardRoutingModule,
        ChartistModule,
        NgbModule,
        MatchHeightModule,
        ReactiveFormsModule,
        FormsModule,
        NgxDatatableModule,
        NgSelectModule,
        NgxLoadingModule.forRoot({
            primaryColour: '#ffffff',
            backdropBorderRadius: '3px'
        })
    ],
    exports: [],
    declarations: [
        UserProfileComponent,
        UserProfileComponent,
        DTFilterComponent,
        ProfileComponent
    ],
    providers: [],
})
export class DashboardModule { }
