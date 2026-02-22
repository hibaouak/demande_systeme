package ma.zyn.app.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commun.ActionDemande;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commun.ActionDemande;
import java.util.List;


@Repository
public interface ActionDemandeDao extends AbstractRepository<ActionDemande,Long>  {
    ActionDemande findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW ActionDemande(item.id,item.libelle) FROM ActionDemande item")
    List<ActionDemande> findAllOptimized();

}
