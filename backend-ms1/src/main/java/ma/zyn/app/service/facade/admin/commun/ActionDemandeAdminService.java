package ma.zyn.app.service.facade.admin.commun;

import java.util.List;
import ma.zyn.app.bean.core.commun.ActionDemande;
import ma.zyn.app.dao.criteria.core.commun.ActionDemandeCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ActionDemandeAdminService {







	ActionDemande create(ActionDemande t);

    ActionDemande update(ActionDemande t);

    List<ActionDemande> update(List<ActionDemande> ts,boolean createIfNotExist);

    ActionDemande findById(Long id);

    ActionDemande findOrSave(ActionDemande t);

    ActionDemande findByReferenceEntity(ActionDemande t);

    ActionDemande findWithAssociatedLists(Long id);

    List<ActionDemande> findAllOptimized();

    List<ActionDemande> findAll();

    List<ActionDemande> findByCriteria(ActionDemandeCriteria criteria);

    List<ActionDemande> findPaginatedByCriteria(ActionDemandeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ActionDemandeCriteria criteria);

    List<ActionDemande> delete(List<ActionDemande> ts);

    boolean deleteById(Long id);

    List<List<ActionDemande>> getToBeSavedAndToBeDeleted(List<ActionDemande> oldList, List<ActionDemande> newList);

}
