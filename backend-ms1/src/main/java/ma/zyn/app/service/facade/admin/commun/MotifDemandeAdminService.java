package ma.zyn.app.service.facade.admin.commun;

import java.util.List;
import ma.zyn.app.bean.core.commun.MotifDemande;
import ma.zyn.app.dao.criteria.core.commun.MotifDemandeCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface MotifDemandeAdminService {







	MotifDemande create(MotifDemande t);

    MotifDemande update(MotifDemande t);

    List<MotifDemande> update(List<MotifDemande> ts,boolean createIfNotExist);

    MotifDemande findById(Long id);

    MotifDemande findOrSave(MotifDemande t);

    MotifDemande findByReferenceEntity(MotifDemande t);

    MotifDemande findWithAssociatedLists(Long id);

    List<MotifDemande> findAllOptimized();

    List<MotifDemande> findAll();

    List<MotifDemande> findByCriteria(MotifDemandeCriteria criteria);

    List<MotifDemande> findPaginatedByCriteria(MotifDemandeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MotifDemandeCriteria criteria);

    List<MotifDemande> delete(List<MotifDemande> ts);

    boolean deleteById(Long id);

    List<List<MotifDemande>> getToBeSavedAndToBeDeleted(List<MotifDemande> oldList, List<MotifDemande> newList);

}
