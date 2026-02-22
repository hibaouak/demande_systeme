package ma.zyn.app.service.impl.admin.commun;



import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.commun.ActionDemande;
import ma.zyn.app.dao.criteria.core.commun.ActionDemandeCriteria;
import ma.zyn.app.dao.facade.core.commun.ActionDemandeDao;
import ma.zyn.app.dao.specification.core.commun.ActionDemandeSpecification;
import ma.zyn.app.service.facade.admin.commun.ActionDemandeAdminService;
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
public class ActionDemandeAdminServiceImpl implements ActionDemandeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ActionDemande update(ActionDemande t) {
        ActionDemande loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ActionDemande.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ActionDemande findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ActionDemande findOrSave(ActionDemande t) {
        if (t != null) {
            ActionDemande result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ActionDemande> findAll() {
        return dao.findAll();
    }

    public List<ActionDemande> findByCriteria(ActionDemandeCriteria criteria) {
        List<ActionDemande> content = null;
        if (criteria != null) {
            ActionDemandeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ActionDemandeSpecification constructSpecification(ActionDemandeCriteria criteria) {
        ActionDemandeSpecification mySpecification =  (ActionDemandeSpecification) RefelexivityUtil.constructObjectUsingOneParam(ActionDemandeSpecification.class, criteria);
        return mySpecification;
    }

    public List<ActionDemande> findPaginatedByCriteria(ActionDemandeCriteria criteria, int page, int pageSize, String order, String sortField) {
        ActionDemandeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ActionDemandeCriteria criteria) {
        ActionDemandeSpecification mySpecification = constructSpecification(criteria);
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
    public List<ActionDemande> delete(List<ActionDemande> list) {
		List<ActionDemande> result = new ArrayList();
        if (list != null) {
            for (ActionDemande t : list) {
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
    public ActionDemande create(ActionDemande t) {
        ActionDemande loaded = findByReferenceEntity(t);
        ActionDemande saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ActionDemande findWithAssociatedLists(Long id){
        ActionDemande result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ActionDemande> update(List<ActionDemande> ts, boolean createIfNotExist) {
        List<ActionDemande> result = new ArrayList<>();
        if (ts != null) {
            for (ActionDemande t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ActionDemande loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ActionDemande t, ActionDemande loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ActionDemande findByReferenceEntity(ActionDemande t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<ActionDemande> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ActionDemande>> getToBeSavedAndToBeDeleted(List<ActionDemande> oldList, List<ActionDemande> newList) {
        List<List<ActionDemande>> result = new ArrayList<>();
        List<ActionDemande> resultDelete = new ArrayList<>();
        List<ActionDemande> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ActionDemande> oldList, List<ActionDemande> newList, List<ActionDemande> resultUpdateOrSave, List<ActionDemande> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ActionDemande myOld = oldList.get(i);
                ActionDemande t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ActionDemande myNew = newList.get(i);
                ActionDemande t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}








    public ActionDemandeAdminServiceImpl(ActionDemandeDao dao) {
        this.dao = dao;
    }

    private ActionDemandeDao dao;
}
