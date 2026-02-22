
import {BaseCriteria} from 'src/app/zynerator/criteria/BaseCriteria.model';

export class ClientCriteria extends BaseCriteria {

    public id: number;
    public code: string;
    public codeLike: string;
    public libelle: string;
    public libelleLike: string;
    public description: string;
    public descriptionLike: string;
    public actif: null | boolean;

}
