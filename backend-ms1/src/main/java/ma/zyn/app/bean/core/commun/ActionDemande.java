package ma.zyn.app.bean.core.commun;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "action_demande")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="action_demande_seq",sequenceName="action_demande_seq",allocationSize=1, initialValue = 1)
public class ActionDemande  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String libelle;

    private String description;

    @Column(columnDefinition = "boolean default false")
    private Boolean actif = false;



    public ActionDemande(){
        super();
    }

    public ActionDemande(Long id){
        this.id = id;
    }

    public ActionDemande(Long id,String libelle){
        this.id = id;
        this.libelle = libelle ;
    }
    public ActionDemande(String libelle){
        this.libelle = libelle ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="action_demande_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getLibelle(){
        return this.libelle;
    }
    public void setLibelle(String libelle){
        this.libelle = libelle;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Boolean  getActif(){
        return this.actif;
    }
    public void setActif(Boolean actif){
        this.actif = actif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionDemande actionDemande = (ActionDemande) o;
        return id != null && id.equals(actionDemande.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

