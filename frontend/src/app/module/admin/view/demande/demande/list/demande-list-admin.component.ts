import {Component, OnInit} from '@angular/core';
import {DemandeAdminService} from 'src/app/shared/service/admin/demande/DemandeAdmin.service';
import {DemandeDto} from 'src/app/shared/model/demande/Demande.model';
import {DemandeCriteria} from 'src/app/shared/criteria/demande/DemandeCriteria.model';


import {ConfirmationService, MessageService,MenuItem} from 'primeng/api';
import {FileTempDto} from 'src/app/zynerator/dto/FileTempDto.model';
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

import {AuthService} from 'src/app/zynerator/security/shared/service/Auth.service';
import {ExportService} from 'src/app/zynerator/util/Export.service';


import {ActionDemandeDto} from 'src/app/shared/model/commun/ActionDemande.model';
import {ActionDemandeAdminService} from 'src/app/shared/service/admin/commun/ActionDemandeAdmin.service';
import {MotifDemandeDto} from 'src/app/shared/model/commun/MotifDemande.model';
import {MotifDemandeAdminService} from 'src/app/shared/service/admin/commun/MotifDemandeAdmin.service';
import {ClientDto} from 'src/app/shared/model/demande/Client.model';
import {ClientAdminService} from 'src/app/shared/service/admin/demande/ClientAdmin.service';


@Component({
  selector: 'app-demande-list-admin',
  standalone: false,
  templateUrl: './demande-list-admin.component.html'
})
export class DemandeListAdminComponent implements OnInit {

    protected fileName = 'Demande';

    protected findByCriteriaShow = false;
    protected cols: any[] = [];
    protected excelPdfButons: MenuItem[];
    protected exportData: any[] = [];
    protected criteriaData: any[] = [];
    protected _totalRecords = 0;
    private _pdfName: string;


    protected datePipe: DatePipe;
    protected messageService: MessageService;
    protected confirmationService: ConfirmationService;
    protected roleService: RoleService;
    protected router: Router;
    protected stringUtilService: StringUtilService;
    protected authService: AuthService;
    protected exportService: ExportService;
    protected excelFile: File | undefined;
    protected enableSecurity = false;


     yesOrNoEnAttenteRetourClient: any[] = [];
    motifDemandes: Array<MotifDemandeDto>;
    actionDemandes: Array<ActionDemandeDto>;
    clients: Array<ClientDto>;


    constructor( private service: DemandeAdminService  , private actionDemandeService: ActionDemandeAdminService, private motifDemandeService: MotifDemandeAdminService, private clientService: ClientAdminService, @Inject(PLATFORM_ID) private platformId?) {
        this.datePipe = ServiceLocator.injector.get(DatePipe);
        this.messageService = ServiceLocator.injector.get(MessageService);
        this.confirmationService = ServiceLocator.injector.get(ConfirmationService);
        this.roleService = ServiceLocator.injector.get(RoleService);
        this.router = ServiceLocator.injector.get(Router);
        this.authService = ServiceLocator.injector.get(AuthService);
        this.exportService = ServiceLocator.injector.get(ExportService);
    }

    ngOnInit(): void {
        this.findPaginatedByCriteria();
        this.initExport();
        this.initCol();
        this.loadMotifDemande();
        this.loadActionDemande();
        this.loadClient();
        this.yesOrNoEnAttenteRetourClient =  [{label: 'EnAttenteRetourClient', value: null},{label: 'Oui', value: 1},{label: 'Non', value: 0}];

    }




    public onExcelFileSelected(event: any): void {
        const input = event.target as HTMLInputElement;
        if (input.files && input.files.length > 0) {
            this.excelFile = input.files[0];
        }
    }

    public importExcel(): void {
        if (this.excelFile) {
            this.service.importExcel(this.excelFile).subscribe(
                response => {
                    this.items = response;
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Success',
                        detail: 'File uploaded successfully!',
                        life: 3000
                    });
                },
                error => {
                    this.messageService.add({
                        severity: 'error',
                        summary: 'Error',
                        detail: 'File uploaded with Error!',
                        life: 3000
                    });
                }
            );
        }
    }

    public findPaginatedByCriteria() {
        this.service.findPaginatedByCriteria(this.criteria).subscribe(paginatedItems => {
            this.items = paginatedItems.list;
            this.totalRecords = paginatedItems.dataSize;
            this.selections = new Array<DemandeDto>();
        }, error => console.log(error));
    }

    public onPage(event: any) {
        this.criteria.page = event.page;
        this.criteria.maxResults = event.rows;
        this.findPaginatedByCriteria();
    }

    public async edit(dto: DemandeDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            console.log(res);
            this.editDialog = true;
        });

    }

    public async view(dto: DemandeDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(res => {
            this.item = res;
            this.viewDialog = true;
        });
    }

    public async openCreate() {
        this.item = new DemandeDto();
        this.createDialog = true;
    }

    public async deleteMultiple() {
        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer ces éléments ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
			rejectButtonProps: {
                label: 'Cancel',
                severity: 'secondary',
                outlined: true,
            },
            acceptButtonProps: {
                label: 'Ok',
            },
            accept: () => {
                this.service.deleteMultiple().subscribe(() => {
                    for (let selection of this.selections) {
                        let index = this.items.findIndex(element => element.id === selection.id);
                        this.items.splice(index,1);
                    }
                    this.selections = new Array<DemandeDto>();
                    this.messageService.add({
                        severity: 'success',
                        summary: 'Succès',
                        detail: 'Les éléments sélectionnés ont été supprimés',
                        life: 3000
                    });

                }, error => console.log(error));
            }
        });
    }


    public isSelectionDisabled(): boolean {
        return this.selections == null || this.selections.length == 0;
    }


    public async delete(dto: DemandeDto) {

        this.confirmationService.confirm({
            message: 'Voulez-vous supprimer cet élément ?',
            header: 'Confirmation',
            icon: 'pi pi-exclamation-triangle',
			rejectButtonProps: {
                label: 'Cancel',
                severity: 'secondary',
                outlined: true,
            },
            acceptButtonProps: {
                label: 'Ok',
            },
            accept: () => {
                this.service.delete(dto).subscribe(status => {
                    if (status > 0) {
                        const position = this.items.indexOf(dto);
                        position > -1 ? this.items.splice(position, 1) : false;
                        this.messageService.add({
                            severity: 'success',
                            summary: 'Succès',
                            detail: 'Element Supprimé',
                            life: 3000
                        });
                    }

                }, error => console.log(error));
            }
        });

    }

    public async duplicate(dto: DemandeDto) {
        this.service.findByIdWithAssociatedList(dto).subscribe(
            res => {
                this.initDuplicate(res);
                this.item = res;
                this.item.id = null;
                this.createDialog = true;
            });
    }

    // TODO : check if correct
    public initExport(): void {
        this.excelPdfButons = [
            {
                label: 'CSV', icon: 'pi pi-file', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterCSV(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'XLS', icon: 'pi pi-file-excel', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterExcel(this.criteriaData, this.exportData, this.fileName);
                }
            },
            {
                label: 'PDF', icon: 'pi pi-file-pdf', command: () => {
                    this.prepareColumnExport();
                    this.exportService.exporterPdf(this.criteriaData, this.exportData, this.fileName);
                }
            }
        ];
    }

    public exportPdf(dto: DemandeDto): void {
        this.service.exportPdf(dto).subscribe((data: ArrayBuffer) => {
            const blob = new Blob([data], {type: 'application/pdf'});
            const url = window.URL.createObjectURL(blob);
            const link = document.createElement('a');
            link.href = url;
            link.download = this.pdfName;
            link.setAttribute('target', '_blank'); // open link in new tab
            link.click();
            window.URL.revokeObjectURL(url);
        }, (error) => {
            console.error(error); // handle any errors that occur
        });
    }

    public showSearch(): void {
        this.findByCriteriaShow = !this.findByCriteriaShow;
    }


    update() {
        this.service.edit().subscribe(data => {
            const myIndex = this.items.findIndex(e => e.id === this.item.id);
            this.items[myIndex] = data;
            this.editDialog = false;
            this.item = new DemandeDto();
        } , error => {
            console.log(error);
        });
    }

    public save() {
        this.service.save().subscribe(item => {
            if (item != null) {
                this.items.push({...item});
                this.createDialog = false;


                this.item = new DemandeDto();
            } else {
                this.messageService.add({severity: 'error', summary: 'Erreurs', detail: 'Element existant'});
            }
        }, error => {
            console.log(error);
        });
    }

// add


    public initCol() {
        this.cols = [
            {field: 'motifDemande?.libelle', header: 'Motif demande'},
            {field: 'actionDemande?.libelle', header: 'Action demande'},
            {field: 'numero', header: 'Numero'},
            {field: 'adresse', header: 'Adresse'},
            {field: 'dateAffectation', header: 'Date affectation'},
            {field: 'dateAnnulation', header: 'Date annulation'},
            {field: 'dateCloture', header: 'Date cloture'},
            {field: 'dateTraitement', header: 'Date traitement'},
            {field: 'enAttenteRetourClient', header: 'En attente retour client'},
            {field: 'client?.libelle', header: 'Client'},
        ];
    }


    public async loadMotifDemande(){
        this.motifDemandeService.findAllOptimized().subscribe(motifDemandes => this.motifDemandes = motifDemandes, error => console.log(error))
    }
    public async loadActionDemande(){
        this.actionDemandeService.findAllOptimized().subscribe(actionDemandes => this.actionDemandes = actionDemandes, error => console.log(error))
    }
    public async loadClient(){
        this.clientService.findAllOptimized().subscribe(clients => this.clients = clients, error => console.log(error))
    }


	public initDuplicate(res: DemandeDto) {
	}


    public prepareColumnExport(): void {
        this.service.findByCriteria(this.criteria).subscribe(
            (allItems) =>{
                this.exportData = allItems.map(e => {
					return {
						'Motif demande': e.motifDemande?.libelle ,
						'Action demande': e.actionDemande?.libelle ,
						'Numero': e.numero ,
						'Adresse': e.adresse ,
						'Date affectation': this.datePipe.transform(e.dateAffectation , 'dd/MM/yyyy hh:mm'),
						'Date annulation': this.datePipe.transform(e.dateAnnulation , 'dd/MM/yyyy hh:mm'),
						'Date cloture': this.datePipe.transform(e.dateCloture , 'dd/MM/yyyy hh:mm'),
						'Date traitement': this.datePipe.transform(e.dateTraitement , 'dd/MM/yyyy hh:mm'),
						'En attente retour client': e.enAttenteRetourClient? 'Vrai' : 'Faux' ,
						'Client': e.client?.libelle ,
					}
				});

            this.criteriaData = [{
            //'Motif demande': this.criteria.motifDemande?.libelle ? this.criteria.motifDemande?.libelle : environment.emptyForExport ,
            //'Action demande': this.criteria.actionDemande?.libelle ? this.criteria.actionDemande?.libelle : environment.emptyForExport ,
                'Numero': this.criteria.numero ? this.criteria.numero : environment.emptyForExport ,
                'Adresse': this.criteria.adresse ? this.criteria.adresse : environment.emptyForExport ,
                'Date affectation Min': this.criteria.dateAffectationFrom ? this.datePipe.transform(this.criteria.dateAffectationFrom , this.dateFormat) : environment.emptyForExport ,
                'Date affectation Max': this.criteria.dateAffectationTo ? this.datePipe.transform(this.criteria.dateAffectationTo , this.dateFormat) : environment.emptyForExport ,
                'Date annulation Min': this.criteria.dateAnnulationFrom ? this.datePipe.transform(this.criteria.dateAnnulationFrom , this.dateFormat) : environment.emptyForExport ,
                'Date annulation Max': this.criteria.dateAnnulationTo ? this.datePipe.transform(this.criteria.dateAnnulationTo , this.dateFormat) : environment.emptyForExport ,
                'Date cloture Min': this.criteria.dateClotureFrom ? this.datePipe.transform(this.criteria.dateClotureFrom , this.dateFormat) : environment.emptyForExport ,
                'Date cloture Max': this.criteria.dateClotureTo ? this.datePipe.transform(this.criteria.dateClotureTo , this.dateFormat) : environment.emptyForExport ,
                'Date traitement Min': this.criteria.dateTraitementFrom ? this.datePipe.transform(this.criteria.dateTraitementFrom , this.dateFormat) : environment.emptyForExport ,
                'Date traitement Max': this.criteria.dateTraitementTo ? this.datePipe.transform(this.criteria.dateTraitementTo , this.dateFormat) : environment.emptyForExport ,
                'En attente retour client': this.criteria.enAttenteRetourClient ? (this.criteria.enAttenteRetourClient ? environment.trueValue : environment.falseValue) : environment.emptyForExport ,
            //'Client': this.criteria.client?.libelle ? this.criteria.client?.libelle : environment.emptyForExport ,
            }];
			}

        )
    }


    get items(): Array<DemandeDto> {
        return this.service.items;
    }

    set items(value: Array<DemandeDto>) {
        this.service.items = value;
    }

    get selections(): Array<DemandeDto> {
        return this.service.selections;
    }

    set selections(value: Array<DemandeDto>) {
        this.service.selections = value;
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

    get editDialog(): boolean {
        return this.service.editDialog;
    }

    set editDialog(value: boolean) {
        this.service.editDialog = value;
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

    get dateFormat() {
        return environment.dateFormatList;
    }


    get totalRecords(): number {
        return this._totalRecords;
    }

    set totalRecords(value: number) {
        this._totalRecords = value;
    }

    get pdfName(): string {
        return this._pdfName;
    }

    set pdfName(value: string) {
        this._pdfName = value;
    }

    get createActionIsValid(): boolean {
        return this.service.createActionIsValid;
    }

    set createActionIsValid(value: boolean) {
        this.service.createActionIsValid = value;
    }


    get editActionIsValid(): boolean {
        return this.service.editActionIsValid;
    }

    set editActionIsValid(value: boolean) {
        this.service.editActionIsValid = value;
    }

    get listActionIsValid(): boolean {
        return this.service.listActionIsValid;
    }

    set listActionIsValid(value: boolean) {
        this.service.listActionIsValid = value;
    }

    get deleteActionIsValid(): boolean {
        return this.service.deleteActionIsValid;
    }

    set deleteActionIsValid(value: boolean) {
        this.service.deleteActionIsValid = value;
    }


    get viewActionIsValid(): boolean {
        return this.service.viewActionIsValid;
    }

    set viewActionIsValid(value: boolean) {
        this.service.viewActionIsValid = value;
    }

    get duplicateActionIsValid(): boolean {
        return this.service.duplicateActionIsValid;
    }

    set duplicateActionIsValid(value: boolean) {
        this.service.duplicateActionIsValid = value;
    }

    get createAction(): string {
        return this.service.createAction;
    }

    set createAction(value: string) {
        this.service.createAction = value;
    }

    get listAction(): string {
        return this.service.listAction;
    }

    set listAction(value: string) {
        this.service.listAction = value;
    }

    get editAction(): string {
        return this.service.editAction;
    }

    set editAction(value: string) {
        this.service.editAction = value;
    }

    get deleteAction(): string {
        return this.service.deleteAction;
    }

    set deleteAction(value: string) {
        this.service.deleteAction = value;
    }

    get viewAction(): string {
        return this.service.viewAction;
    }

    set viewAction(value: string) {
        this.service.viewAction = value;
    }

    get duplicateAction(): string {
        return this.service.duplicateAction;
    }

    set duplicateAction(value: string) {
        this.service.duplicateAction = value;
    }

    get entityName(): string {
        return this.service.entityName;
    }

    set entityName(value: string) {
        this.service.entityName = value;
    }
}
