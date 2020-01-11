import { Component, ViewChild, TemplateRef, OnInit } from '@angular/core';
import { DatatableComponent } from "@swimlane/ngx-datatable/release";
import { ComponentInteractionService } from '../../shared/behavior-subject-service/component-interaction.service';
import { Router } from '@angular/router';
import swal from 'sweetalert2';
import { LoaderComponentService } from '../shared/behavior-subject-service/loader-component-interaction.service';
import * as alertFunctions from '../../shared/data/sweet-alerts';
import { PdfgenerateService } from '../service/pdfgenerate.service';
import { ProfileModel } from '../helper/model/profile-model';
import { ProfileService } from '../service/profile.service';



@Component({
    selector: 'app-dt-filter',
    templateUrl: './dt-filter.component.html',
    styleUrls: ['./dt-filter.component.scss']
})

export class DTFilterComponent implements OnInit {

    @ViewChild('hdrTpl') hdrTpl: TemplateRef<any>;
    rows = [];
    columns = [];
    temp = [];
    donar: ProfileModel = new ProfileModel();

    constructor(private donarService: ProfileService, private router: Router, private loaderComponentService: LoaderComponentService, private pdfgenerateService: PdfgenerateService) { }

    ngOnInit() {

        this.columns = [
            { name: 'First Name', prop: 'firstName', headerTemplate: this.hdrTpl },
            { name: 'Last Name ', prop: 'lastName', headerTemplate: this.hdrTpl },
            { name: 'Mobile Number', prop: 'phoneNumber', headerTemplate: this.hdrTpl },
            { name: 'City', prop: 'city', headerTemplate: this.hdrTpl },
            { name: 'Company', prop: 'cName', headerTemplate: this.hdrTpl }
        ];

        this.loadAllDonars();


    }

    loadAllDonars(): void {
        this.loaderComponentService.emitChange(true);
        this.donarService.getAllDonars().subscribe(response => {
            this.loaderComponentService.emitChange(false);
            this.rows = response._embedded.profileEntities;
            this.temp = this.rows;
        },
            error => {
                this.loaderComponentService.emitChange(false);
                alertFunctions.custometypeError("Oops.!!", "Something went wrong..");
            });
    }


}