import {Component, OnInit, Input} from '@angular/core';
import {ConfirmationService, MessageService} from 'primeng/api';

import {DatePipe} from '@angular/common';
import {Router} from '@angular/router';
import {Inject, Injectable, PLATFORM_ID} from '@angular/core';


import {environment} from 'src/environments/environment';

import {RoleService} from 'src/app/zynerator/security/shared/service/Role.service';
import {StringUtilService} from 'src/app/zynerator/util/StringUtil.service';
import {ServiceLocator} from 'src/app/zynerator/service/ServiceLocator';




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
  selector: 'app-demande-create-admin',
  standalone: false,
  templateUrl: './demande-create-admin.component.html'
})
export class DemandeCreateAdminComponent  implements OnInit {

	protected _submitted = false;
    protected _errorMessages = new Array<string>();

    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    private _activeTab = 0;



   private _validDemandeNumero = true;
    private _validMotifDemandeCode = true;
    private _validMotifDemandeLibelle = true;
    private _validActionDemandeCode = true;
    private _validActionDemandeLibelle = true;
    private _validClientCode = true;
    private _validClientLibelle = true;

	constructor(private service: DemandeAdminService , private actionDemandeService: ActionDemandeAdminService, private motifDemandeService: MotifDemandeAdminService, private clientService: ClientAdminService, @Inject(PLATFORM_ID) private platformId? ) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.stringUtilService = ServiceLocator.injector.get(StringUtilService);
    }

    ngOnInit(): void {
        this.motifDemandeService.findAll().subscribe((data) => this.motifDemandes = data);
        this.actionDemandeService.findAll().subscribe((data) => this.actionDemandes = data);
        this.clientService.findAll().subscribe((data) => this.clients = data);
    }



    public save(): void {
        this.submitted = true;
        this.validateForm();
        if (this.errorMessages.length === 0) {
            this.saveWithShowOption(false);
        } else {
            this.messageService.add({severity: 'error',summary: 'Erreurs',detail: 'Merci de corrigé les erreurs sur le formulaire'});
        }
    }

    public saveWithShowOption(showList: boolean) {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;
                this.submitted = false;
                this.item = new DemandeDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }

        }, error => {
            console.log(error);
        });
    }


    public hideCreateDialog() {
        this.createDialog = false;
        this.setValidation(true);
    }





    public  setValidation(value: boolean){
        this.validDemandeNumero = value;
    }



    public  validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateDemandeNumero();
    }

    public validateDemandeNumero(){
        if (this.stringUtilService.isEmpty(this.item.numero)) {
        this.errorMessages.push('Numero non valide');
        this.validDemandeNumero = false;
        } else {
            this.validDemandeNumero = true;
        }
    }


    public async openCreateClient(client: string) {
    const isPermistted = await this.roleService.isPermitted('Client', 'add');
    if(isPermistted) {
         this.client = new ClientDto();
         this.createClientDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
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
    get createClientDialog(): boolean {
        return this.clientService.createDialog;
    }
    set createClientDialog(value: boolean) {
        this.clientService.createDialog= value;
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
    get createActionDemandeDialog(): boolean {
        return this.actionDemandeService.createDialog;
    }
    set createActionDemandeDialog(value: boolean) {
        this.actionDemandeService.createDialog= value;
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
    get createMotifDemandeDialog(): boolean {
        return this.motifDemandeService.createDialog;
    }
    set createMotifDemandeDialog(value: boolean) {
        this.motifDemandeService.createDialog= value;
    }



    get validDemandeNumero(): boolean {
        return this._validDemandeNumero;
    }

    set validDemandeNumero(value: boolean) {
         this._validDemandeNumero = value;
    }

    get validMotifDemandeCode(): boolean {
        return this._validMotifDemandeCode;
    }
    set validMotifDemandeCode(value: boolean) {
        this._validMotifDemandeCode = value;
    }
    get validMotifDemandeLibelle(): boolean {
        return this._validMotifDemandeLibelle;
    }
    set validMotifDemandeLibelle(value: boolean) {
        this._validMotifDemandeLibelle = value;
    }
    get validActionDemandeCode(): boolean {
        return this._validActionDemandeCode;
    }
    set validActionDemandeCode(value: boolean) {
        this._validActionDemandeCode = value;
    }
    get validActionDemandeLibelle(): boolean {
        return this._validActionDemandeLibelle;
    }
    set validActionDemandeLibelle(value: boolean) {
        this._validActionDemandeLibelle = value;
    }
    get validClientCode(): boolean {
        return this._validClientCode;
    }
    set validClientCode(value: boolean) {
        this._validClientCode = value;
    }
    get validClientLibelle(): boolean {
        return this._validClientLibelle;
    }
    set validClientLibelle(value: boolean) {
        this._validClientLibelle = value;
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

    get createDialog(): boolean {
        return this.service.createDialog;
    }

    set createDialog(value: boolean) {
        this.service.createDialog = value;
    }

    get criteria(): DemandeCriteria {
        return this.service.criteria;
    }

    set criteria(value: DemandeCriteria) {
        this.service.criteria = value;
    }

    get dateFormat() {
        return environment.dateFormatCreate;
    }

    get dateFormatColumn() {
        return environment.dateFormatCreate;
    }

    get submitted(): boolean {
        return this._submitted;
    }

    set submitted(value: boolean) {
        this._submitted = value;
    }

    get errorMessages(): string[] {
        if (this._errorMessages == null) {
            this._errorMessages = new Array<string>();
        }
        return this._errorMessages;
    }

    set errorMessages(value: string[]) {
        this._errorMessages = value;
    }

    get validate(): boolean {
        return this.service.validate;
    }

    set validate(value: boolean) {
        this.service.validate = value;
    }


    get activeTab(): number {
        return this._activeTab;
    }

    set activeTab(value: number) {
        this._activeTab = value;
    }

}
