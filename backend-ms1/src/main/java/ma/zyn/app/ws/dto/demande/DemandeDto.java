package  ma.zyn.app.ws.dto.demande;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.ws.dto.commun.ActionDemandeDto;
import ma.zyn.app.ws.dto.commun.MotifDemandeDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class DemandeDto  extends AuditBaseDto {

    private String numero  ;
    private String adresse  ;
    private String dateAffectation ;
    private String dateAnnulation ;
    private String dateCloture ;
    private String dateTraitement ;
    private Boolean enAttenteRetourClient  ;

    private MotifDemandeDto motifDemande ;
    private ActionDemandeDto actionDemande ;
    private ClientDto client ;



    public DemandeDto(){
        super();
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


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateAffectation(){
        return this.dateAffectation;
    }
    public void setDateAffectation(String dateAffectation){
        this.dateAffectation = dateAffectation;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateAnnulation(){
        return this.dateAnnulation;
    }
    public void setDateAnnulation(String dateAnnulation){
        this.dateAnnulation = dateAnnulation;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateCloture(){
        return this.dateCloture;
    }
    public void setDateCloture(String dateCloture){
        this.dateCloture = dateCloture;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateTraitement(){
        return this.dateTraitement;
    }
    public void setDateTraitement(String dateTraitement){
        this.dateTraitement = dateTraitement;
    }


    public Boolean getEnAttenteRetourClient(){
        return this.enAttenteRetourClient;
    }
    public void setEnAttenteRetourClient(Boolean enAttenteRetourClient){
        this.enAttenteRetourClient = enAttenteRetourClient;
    }


    public MotifDemandeDto getMotifDemande(){
        return this.motifDemande;
    }

    public void setMotifDemande(MotifDemandeDto motifDemande){
        this.motifDemande = motifDemande;
    }
    public ActionDemandeDto getActionDemande(){
        return this.actionDemande;
    }

    public void setActionDemande(ActionDemandeDto actionDemande){
        this.actionDemande = actionDemande;
    }
    public ClientDto getClient(){
        return this.client;
    }

    public void setClient(ClientDto client){
        this.client = client;
    }






}
