import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { DTFilterComponent } from './data-tables-donar/dt-filter.component';
import { AuthGuard } from './helper/guards/auth-guard';
import { ProfileComponent } from './profile/profile.component';
const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'UserProfile',
        component: UserProfileComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'DashBoardGrid',
        component: DTFilterComponent,
        canActivate: [AuthGuard],
      },
      {
        path: 'AddProfile',
        component: ProfileComponent,
        canActivate: [AuthGuard],
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class DashboardRoutingModule { }
