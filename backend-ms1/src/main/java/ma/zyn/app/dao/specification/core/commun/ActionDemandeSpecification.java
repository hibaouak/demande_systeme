package  ma.zyn.app.dao.specification.core.commun;

import ma.zyn.app.dao.criteria.core.commun.ActionDemandeCriteria;
import ma.zyn.app.bean.core.commun.ActionDemande;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ActionDemandeSpecification extends  AbstractSpecification<ActionDemandeCriteria, ActionDemande>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateBool("actif", criteria.getActif());
    }

    public ActionDemandeSpecification(ActionDemandeCriteria criteria) {
        super(criteria);
    }

    public ActionDemandeSpecification(ActionDemandeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
