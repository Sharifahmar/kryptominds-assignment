import { Component, Output, EventEmitter, OnDestroy, OnInit, AfterViewInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { LayoutService } from '../services/layout.service';
import { Subscription } from 'rxjs';
import { ConfigService } from '../services/config.service';
import { Router } from '@angular/router';
import { ComponentInteractionService } from '../../dashboard/shared/behavior-subject-service/component-interaction.service';
import { UserProfileService } from '../services/user-profile.service';


@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.scss"]
})
export class NavbarComponent implements OnInit, AfterViewInit, OnDestroy {
  currentLang = "en";
  toggleClass = "ft-maximize";
  placement = "bottom-right";
  public isCollapsed = true;
  layoutSub: Subscription;
  @Output()
  toggleHideSidebar = new EventEmitter<Object>();

  public config: any = {};

  userProfile: any = {};

  navBarfirstNameObj:any={};

  navBarfirstName:string;

  constructor(private router: Router, public translate: TranslateService, private layoutService: LayoutService, private configService: ConfigService, private userProfileService: UserProfileService, private componentInteractionService: ComponentInteractionService) {
    const browserLang: string = translate.getBrowserLang();
    translate.use(browserLang.match(/en|es|pt|de/) ? browserLang : "en");

    this.layoutSub = layoutService.changeEmitted$.subscribe(
      direction => {
        const dir = direction.direction;
        if (dir === "rtl") {
          this.placement = "bottom-left";
        } else if (dir === "ltr") {
          this.placement = "bottom-right";
        }
      });
  }

  ngOnInit() {
    this.config = this.configService.templateConf;
    this.loadDetails();
  }

  ngAfterViewInit() {
    if (this.config.layout.dir) {
      setTimeout(() => {
        const dir = this.config.layout.dir;
        if (dir === "rtl") {
          this.placement = "bottom-left";
        } else if (dir === "ltr") {
          this.placement = "bottom-right";
        }
      }, 0);

    }
  }

  ngOnDestroy() {
    if (this.layoutSub) {
      this.layoutSub.unsubscribe();
    }
  }

  ChangeLanguage(language: string) {
    this.translate.use(language);
  }

  ToggleClass() {
    if (this.toggleClass === "ft-maximize") {
      this.toggleClass = "ft-minimize";
    } else {
      this.toggleClass = "ft-maximize";
    }
  }

  toggleNotificationSidebar() {
    this.layoutService.emitNotiSidebarChange(true);
  }

  toggleSidebar() {
    const appSidebar = document.getElementsByClassName("app-sidebar")[0];
    if (appSidebar.classList.contains("hide-sidebar")) {
      this.toggleHideSidebar.emit(false);
    } else {
      this.toggleHideSidebar.emit(true);
    }
  }

  logout(): void {
    sessionStorage.clear();
    this.router.navigate(['pages/login']);

  }

  updateDetails(): void {
    let obj = {
      "status": true
    }
    this.userProfileService.userDetails(obj).subscribe(
      response => {
        this.userProfile = response;
        //console.log("first********" +this.userProfile.firstName)
        this.componentInteractionService.getResponseObject(this.userProfile);

      },
      error => {


      }
    )
  }

  loadDetails(): void {
    let obj = {
      "status": true
    }
    this.userProfileService.userDetails(obj).subscribe(
      response => {
      this.navBarfirstNameObj=response;
      this.navBarfirstName=this.navBarfirstNameObj.firstName;
      },
      error => {
      }
    )}

}
