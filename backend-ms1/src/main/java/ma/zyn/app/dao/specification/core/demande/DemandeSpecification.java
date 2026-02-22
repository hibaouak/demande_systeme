package  ma.zyn.app.dao.specification.core.demande;

import ma.zyn.app.dao.criteria.core.demande.DemandeCriteria;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class DemandeSpecification extends  AbstractSpecification<DemandeCriteria, Demande>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("numero", criteria.getNumero(),criteria.getNumeroLike());
        addPredicate("adresse", criteria.getAdresse(),criteria.getAdresseLike());
        addPredicate("dateAffectation", criteria.getDateAffectation(), criteria.getDateAffectationFrom(), criteria.getDateAffectationTo());
        addPredicate("dateAnnulation", criteria.getDateAnnulation(), criteria.getDateAnnulationFrom(), criteria.getDateAnnulationTo());
        addPredicate("dateCloture", criteria.getDateCloture(), criteria.getDateClotureFrom(), criteria.getDateClotureTo());
        addPredicate("dateTraitement", criteria.getDateTraitement(), criteria.getDateTraitementFrom(), criteria.getDateTraitementTo());
        addPredicateBool("enAttenteRetourClient", criteria.getEnAttenteRetourClient());
        addPredicateFk("motifDemande","id", criteria.getMotifDemande()==null?null:criteria.getMotifDemande().getId());
        addPredicateFk("motifDemande","id", criteria.getMotifDemandes());
        addPredicateFk("motifDemande","code", criteria.getMotifDemande()==null?null:criteria.getMotifDemande().getCode());
        addPredicateFk("actionDemande","id", criteria.getActionDemande()==null?null:criteria.getActionDemande().getId());
        addPredicateFk("actionDemande","id", criteria.getActionDemandes());
        addPredicateFk("actionDemande","code", criteria.getActionDemande()==null?null:criteria.getActionDemande().getCode());
        addPredicateFk("client","id", criteria.getClient()==null?null:criteria.getClient().getId());
        addPredicateFk("client","id", criteria.getClients());
        addPredicateFk("client","code", criteria.getClient()==null?null:criteria.getClient().getCode());
    }

    public DemandeSpecification(DemandeCriteria criteria) {
        super(criteria);
    }

    public DemandeSpecification(DemandeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
