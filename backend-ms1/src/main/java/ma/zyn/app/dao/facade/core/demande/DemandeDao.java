package ma.zyn.app.dao.facade.core.demande;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.demande.Demande;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.demande.Demande;
import java.util.List;


@Repository
public interface DemandeDao extends AbstractRepository<Demande,Long>  {
    Demande findByNumero(String numero);
    int deleteByNumero(String numero);

    List<Demande> findByMotifDemandeId(Long id);
    int deleteByMotifDemandeId(Long id);
    long countByMotifDemandeCode(String code);
    List<Demande> findByActionDemandeId(Long id);
    int deleteByActionDemandeId(Long id);
    long countByActionDemandeCode(String code);
    List<Demande> findByClientId(Long id);
    int deleteByClientId(Long id);
    long countByClientCode(String code);

    @Query("SELECT NEW Demande(item.id,item.numero) FROM Demande item")
    List<Demande> findAllOptimized();

}
