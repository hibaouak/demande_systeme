package  ma.zyn.app.dao.specification.core.demande;

import ma.zyn.app.dao.criteria.core.demande.ClientCriteria;
import ma.zyn.app.bean.core.demande.Client;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ClientSpecification extends  AbstractSpecification<ClientCriteria, Client>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateBool("actif", criteria.getActif());
    }

    public ClientSpecification(ClientCriteria criteria) {
        super(criteria);
    }

    public ClientSpecification(ClientCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
