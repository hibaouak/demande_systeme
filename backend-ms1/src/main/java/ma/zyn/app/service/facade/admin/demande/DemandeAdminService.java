package ma.zyn.app.service.facade.admin.demande;

import java.util.List;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.dao.criteria.core.demande.DemandeCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface DemandeAdminService {



    List<Demande> findByMotifDemandeId(Long id);
    int deleteByMotifDemandeId(Long id);
    long countByMotifDemandeCode(String code);
    List<Demande> findByActionDemandeId(Long id);
    int deleteByActionDemandeId(Long id);
    long countByActionDemandeCode(String code);
    List<Demande> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);




	Demande create(Demande t);

    Demande update(Demande t);

    List<Demande> update(List<Demande> ts,boolean createIfNotExist);

    Demande findById(Long id);

    Demande findOrSave(Demande t);

    Demande findByReferenceEntity(Demande t);

    Demande findWithAssociatedLists(Long id);

    List<Demande> findAllOptimized();

    List<Demande> findAll();

    List<Demande> findByCriteria(DemandeCriteria criteria);

    List<Demande> findPaginatedByCriteria(DemandeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DemandeCriteria criteria);

    List<Demande> delete(List<Demande> ts);

    boolean deleteById(Long id);

    List<List<Demande>> getToBeSavedAndToBeDeleted(List<Demande> oldList, List<Demande> newList);

}
