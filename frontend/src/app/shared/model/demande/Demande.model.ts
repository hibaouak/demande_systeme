import {ActionDemandeDto} from '../commun/ActionDemande.model';
import {MotifDemandeDto} from '../commun/MotifDemande.model';
import {ClientDto} from './Client.model';

import {BaseDto} from 'src/app/zynerator/dto/BaseDto.model';


export class DemandeDto extends BaseDto{

    public numero: string;

    public adresse: string;

   public dateAffectation: Date;

   public dateAnnulation: Date;

   public dateCloture: Date;

   public dateTraitement: Date;

   public enAttenteRetourClient: null | boolean;

    public motifDemande: MotifDemandeDto ;
    public actionDemande: ActionDemandeDto ;
    public client: ClientDto ;


    constructor() {
        super();

        this.numero = '';
        this.adresse = '';
        this.dateAffectation = null;
        this.dateAnnulation = null;
        this.dateCloture = null;
        this.dateTraitement = null;
        this.enAttenteRetourClient = null;

        }

}
