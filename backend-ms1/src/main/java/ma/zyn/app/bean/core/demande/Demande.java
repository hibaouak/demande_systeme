package ma.zyn.app.bean.core.demande;


import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.commun.ActionDemande;
import ma.zyn.app.bean.core.commun.MotifDemande;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "demande")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="demande_seq",sequenceName="demande_seq",allocationSize=1, initialValue = 1)
public class Demande  extends BaseEntity     {




    @Column(length = 500)
    private String numero;

    @Column(length = 500)
    private String adresse;

    private LocalDateTime dateAffectation ;

    private LocalDateTime dateAnnulation ;

    private LocalDateTime dateCloture ;

    private LocalDateTime dateTraitement ;

    @Column(columnDefinition = "boolean default false")
    private Boolean enAttenteRetourClient = false;

    private MotifDemande motifDemande ;
    private ActionDemande actionDemande ;
    private Client client ;


    public Demande(){
        super();
    }

    public Demande(Long id){
        this.id = id;
    }

    public Demande(Long id,String numero){
        this.id = id;
        this.numero = numero ;
    }
    public Demande(String numero){
        this.numero = numero ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="demande_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motif_demande")
    public MotifDemande getMotifDemande(){
        return this.motifDemande;
    }
    public void setMotifDemande(MotifDemande motifDemande){
        this.motifDemande = motifDemande;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_demande")
    public ActionDemande getActionDemande(){
        return this.actionDemande;
    }
    public void setActionDemande(ActionDemande actionDemande){
        this.actionDemande = actionDemande;
    }
    public String getNumero(){
        return this.numero;
    }
    public void setNumero(String numero){
        this.numero = numero;
    }
    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public LocalDateTime getDateAffectation(){
        return this.dateAffectation;
    }
    public void setDateAffectation(LocalDateTime dateAffectation){
        this.dateAffectation = dateAffectation;
    }
    public LocalDateTime getDateAnnulation(){
        return this.dateAnnulation;
    }
    public void setDateAnnulation(LocalDateTime dateAnnulation){
        this.dateAnnulation = dateAnnulation;
    }
    public LocalDateTime getDateCloture(){
        return this.dateCloture;
    }
    public void setDateCloture(LocalDateTime dateCloture){
        this.dateCloture = dateCloture;
    }
    public LocalDateTime getDateTraitement(){
        return this.dateTraitement;
    }
    public void setDateTraitement(LocalDateTime dateTraitement){
        this.dateTraitement = dateTraitement;
    }
    public Boolean  getEnAttenteRetourClient(){
        return this.enAttenteRetourClient;
    }
    public void setEnAttenteRetourClient(Boolean enAttenteRetourClient){
        this.enAttenteRetourClient = enAttenteRetourClient;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client")
    public Client getClient(){
        return this.client;
    }
    public void setClient(Client client){
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demande demande = (Demande) o;
        return id != null && id.equals(demande.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

