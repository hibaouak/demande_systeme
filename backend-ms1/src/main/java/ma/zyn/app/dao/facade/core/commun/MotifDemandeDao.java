package ma.zyn.app.dao.facade.core.commun;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.commun.MotifDemande;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.commun.MotifDemande;
import java.util.List;


@Repository
public interface MotifDemandeDao extends AbstractRepository<MotifDemande,Long>  {
    MotifDemande findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW MotifDemande(item.id,item.libelle) FROM MotifDemande item")
    List<MotifDemande> findAllOptimized();

}
