package  ma.zyn.app.dao.specification.core.commun;

import ma.zyn.app.dao.criteria.core.commun.MotifDemandeCriteria;
import ma.zyn.app.bean.core.commun.MotifDemande;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class MotifDemandeSpecification extends  AbstractSpecification<MotifDemandeCriteria, MotifDemande>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateBool("actif", criteria.getActif());
    }

    public MotifDemandeSpecification(MotifDemandeCriteria criteria) {
        super(criteria);
    }

    public MotifDemandeSpecification(MotifDemandeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
