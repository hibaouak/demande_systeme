
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class MotifDemandeCriteria extends BaseCriteria {

    public id: number;
    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public actif: null | boolean;

}
