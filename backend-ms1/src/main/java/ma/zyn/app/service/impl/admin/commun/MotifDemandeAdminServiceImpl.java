package ma.zyn.app.service.impl.admin.commun;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commun.MotifDemande;
import ma.zyn.app.dao.criteria.core.commun.MotifDemandeCriteria;
import ma.zyn.app.dao.facade.core.commun.MotifDemandeDao;
import ma.zyn.app.dao.specification.core.commun.MotifDemandeSpecification;
import ma.zyn.app.service.facade.admin.commun.MotifDemandeAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class MotifDemandeAdminServiceImpl implements MotifDemandeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public MotifDemande update(MotifDemande t) {
        MotifDemande loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{MotifDemande.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public MotifDemande findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public MotifDemande findOrSave(MotifDemande t) {
        if (t != null) {
            MotifDemande result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<MotifDemande> findAll() {
        return dao.findAll();
    }

    public List<MotifDemande> findByCriteria(MotifDemandeCriteria criteria) {
        List<MotifDemande> content = null;
        if (criteria != null) {
            MotifDemandeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private MotifDemandeSpecification constructSpecification(MotifDemandeCriteria criteria) {
        MotifDemandeSpecification mySpecification =  (MotifDemandeSpecification) RefelexivityUtil.constructObjectUsingOneParam(MotifDemandeSpecification.class, criteria);
        return mySpecification;
    }

    public List<MotifDemande> findPaginatedByCriteria(MotifDemandeCriteria criteria, int page, int pageSize, String order, String sortField) {
        MotifDemandeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(MotifDemandeCriteria criteria) {
        MotifDemandeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<MotifDemande> delete(List<MotifDemande> list) {
		List<MotifDemande> result = new ArrayList();
        if (list != null) {
            for (MotifDemande t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public MotifDemande create(MotifDemande t) {
        MotifDemande loaded = findByReferenceEntity(t);
        MotifDemande saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public MotifDemande findWithAssociatedLists(Long id){
        MotifDemande result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<MotifDemande> update(List<MotifDemande> ts, boolean createIfNotExist) {
        List<MotifDemande> result = new ArrayList<>();
        if (ts != null) {
            for (MotifDemande t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    MotifDemande loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, MotifDemande t, MotifDemande loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public MotifDemande findByReferenceEntity(MotifDemande t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<MotifDemande> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<MotifDemande>> getToBeSavedAndToBeDeleted(List<MotifDemande> oldList, List<MotifDemande> newList) {
        List<List<MotifDemande>> result = new ArrayList<>();
        List<MotifDemande> resultDelete = new ArrayList<>();
        List<MotifDemande> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<MotifDemande> oldList, List<MotifDemande> newList, List<MotifDemande> resultUpdateOrSave, List<MotifDemande> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                MotifDemande myOld = oldList.get(i);
                MotifDemande t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                MotifDemande myNew = newList.get(i);
                MotifDemande t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public MotifDemandeAdminServiceImpl(MotifDemandeDao dao) {
        this.dao = dao;
    }

    private MotifDemandeDao dao;
}
