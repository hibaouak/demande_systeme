import {Component, OnInit} from '@angular/core';


import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {AbstractService} from 'src/app/zynerator/service/AbstractService';
import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';
import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';


import {DemandeAdminService} from 'src/app/shared/service/admin/demande/DemandeAdmin.service';
import {DemandeDto} from 'src/app/shared/model/demande/Demande.model';
import {DemandeCriteria} from 'src/app/shared/criteria/demande/DemandeCriteria.model';

import {ActionDemandeDto} from 'src/app/shared/model/commun/ActionDemande.model';
import {ActionDemandeAdminService} from 'src/app/shared/service/admin/commun/ActionDemandeAdmin.service';
import {MotifDemandeDto} from 'src/app/shared/model/commun/MotifDemande.model';
import {MotifDemandeAdminService} from 'src/app/shared/service/admin/commun/MotifDemandeAdmin.service';
import {ClientDto} from 'src/app/shared/model/demande/Client.model';
import {ClientAdminService} from 'src/app/shared/service/admin/demande/ClientAdmin.service';
@Component({
  selector: 'app-demande-view-admin',
  standalone: false,
  templateUrl: './demande-view-admin.component.html'
})
export class DemandeViewAdminComponent implements OnInit {


	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;



    constructor(private service: DemandeAdminService, private actionDemandeService: ActionDemandeAdminService, private motifDemandeService: MotifDemandeAdminService, private clientService: ClientAdminService){
		this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
	}

    ngOnInit(): void {
    }


    get client(): ClientDto {
        return this.clientService.item;
    }
    set client(value: ClientDto) {
        this.clientService.item = value;
    }
    get clients(): Array<ClientDto> {
        return this.clientService.items;
    }
    set clients(value: Array<ClientDto>) {
        this.clientService.items = value;
    }
    get actionDemande(): ActionDemandeDto {
        return this.actionDemandeService.item;
    }
    set actionDemande(value: ActionDemandeDto) {
        this.actionDemandeService.item = value;
    }
    get actionDemandes(): Array<ActionDemandeDto> {
        return this.actionDemandeService.items;
    }
    set actionDemandes(value: Array<ActionDemandeDto>) {
        this.actionDemandeService.items = value;
    }
    get motifDemande(): MotifDemandeDto {
        return this.motifDemandeService.item;
    }
    set motifDemande(value: MotifDemandeDto) {
        this.motifDemandeService.item = value;
    }
    get motifDemandes(): Array<MotifDemandeDto> {
        return this.motifDemandeService.items;
    }
    set motifDemandes(value: Array<MotifDemandeDto>) {
        this.motifDemandeService.items = value;
    }

    public hideViewDialog() {
        this.viewDialog = false;
    }

    get items(): Array<DemandeDto> {
        return this.service.items;
    }

    set items(value: Array<DemandeDto>) {
        this.service.items = value;
    }

    get item(): DemandeDto {
        return this.service.item;
    }

    set item(value: DemandeDto) {
        this.service.item = value;
    }

    get viewDialog(): boolean {
        return this.service.viewDialog;
    }

    set viewDialog(value: boolean) {
        this.service.viewDialog = value;
    }

    get criteria(): DemandeCriteria {
        return this.service.criteria;
    }

    set criteria(value: DemandeCriteria) {
        this.service.criteria = value;
    }

    get dateFormat(){
        return environment.dateFormatView;
    }

    get dateFormatColumn(){
        return environment.dateFormatList;
    }


}
