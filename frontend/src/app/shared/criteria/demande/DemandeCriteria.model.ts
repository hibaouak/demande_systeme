import {ActionDemandeCriteria} from '../commun/ActionDemandeCriteria.model';
import {MotifDemandeCriteria} from '../commun/MotifDemandeCriteria.model';
import {ClientCriteria} from './ClientCriteria.model';

import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class DemandeCriteria extends BaseCriteria {

    public id: number;
    public numero: string;
    public numeroLike: string;
    public adresse: string;
    public adresseLike: string;
    public dateAffectation: Date;
    public dateAffectationFrom: Date;
    public dateAffectationTo: Date;
    public dateAnnulation: Date;
    public dateAnnulationFrom: Date;
    public dateAnnulationTo: Date;
    public dateCloture: Date;
    public dateClotureFrom: Date;
    public dateClotureTo: Date;
    public dateTraitement: Date;
    public dateTraitementFrom: Date;
    public dateTraitementTo: Date;
    public enAttenteRetourClient: null | boolean;

}
