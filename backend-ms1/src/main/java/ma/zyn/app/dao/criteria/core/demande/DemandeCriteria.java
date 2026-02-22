package  ma.zyn.app.dao.criteria.core.demande;


import ma.zyn.app.dao.criteria.core.commun.ActionDemandeCriteria;
import ma.zyn.app.dao.criteria.core.commun.MotifDemandeCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class DemandeCriteria extends  BaseCriteria  {

    private String numero;
    private String numeroLike;
    private String adresse;
    private String adresseLike;
    private LocalDateTime dateAffectation;
    private LocalDateTime dateAffectationFrom;
    private LocalDateTime dateAffectationTo;
    private LocalDateTime dateAnnulation;
    private LocalDateTime dateAnnulationFrom;
    private LocalDateTime dateAnnulationTo;
    private LocalDateTime dateCloture;
    private LocalDateTime dateClotureFrom;
    private LocalDateTime dateClotureTo;
    private LocalDateTime dateTraitement;
    private LocalDateTime dateTraitementFrom;
    private LocalDateTime dateTraitementTo;
    private Boolean enAttenteRetourClient;

    private MotifDemandeCriteria motifDemande ;
    private List<MotifDemandeCriteria> motifDemandes ;
    private ActionDemandeCriteria actionDemande ;
    private List<ActionDemandeCriteria> actionDemandes ;
    private ClientCriteria client ;
    private List<ClientCriteria> clients ;


    public String getNumero(){
        return this.numero;
    }
    public void setNumero(String numero){
        this.numero = numero;
    }
    public String getNumeroLike(){
        return this.numeroLike;
    }
    public void setNumeroLike(String numeroLike){
        this.numeroLike = numeroLike;
    }

    public String getAdresse(){
        return this.adresse;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }
    public String getAdresseLike(){
        return this.adresseLike;
    }
    public void setAdresseLike(String adresseLike){
        this.adresseLike = adresseLike;
    }

    public LocalDateTime getDateAffectation(){
        return this.dateAffectation;
    }
    public void setDateAffectation(LocalDateTime dateAffectation){
        this.dateAffectation = dateAffectation;
    }
    public LocalDateTime getDateAffectationFrom(){
        return this.dateAffectationFrom;
    }
    public void setDateAffectationFrom(LocalDateTime dateAffectationFrom){
        this.dateAffectationFrom = dateAffectationFrom;
    }
    public LocalDateTime getDateAffectationTo(){
        return this.dateAffectationTo;
    }
    public void setDateAffectationTo(LocalDateTime dateAffectationTo){
        this.dateAffectationTo = dateAffectationTo;
    }
    public LocalDateTime getDateAnnulation(){
        return this.dateAnnulation;
    }
    public void setDateAnnulation(LocalDateTime dateAnnulation){
        this.dateAnnulation = dateAnnulation;
    }
    public LocalDateTime getDateAnnulationFrom(){
        return this.dateAnnulationFrom;
    }
    public void setDateAnnulationFrom(LocalDateTime dateAnnulationFrom){
        this.dateAnnulationFrom = dateAnnulationFrom;
    }
    public LocalDateTime getDateAnnulationTo(){
        return this.dateAnnulationTo;
    }
    public void setDateAnnulationTo(LocalDateTime dateAnnulationTo){
        this.dateAnnulationTo = dateAnnulationTo;
    }
    public LocalDateTime getDateCloture(){
        return this.dateCloture;
    }
    public void setDateCloture(LocalDateTime dateCloture){
        this.dateCloture = dateCloture;
    }
    public LocalDateTime getDateClotureFrom(){
        return this.dateClotureFrom;
    }
    public void setDateClotureFrom(LocalDateTime dateClotureFrom){
        this.dateClotureFrom = dateClotureFrom;
    }
    public LocalDateTime getDateClotureTo(){
        return this.dateClotureTo;
    }
    public void setDateClotureTo(LocalDateTime dateClotureTo){
        this.dateClotureTo = dateClotureTo;
    }
    public LocalDateTime getDateTraitement(){
        return this.dateTraitement;
    }
    public void setDateTraitement(LocalDateTime dateTraitement){
        this.dateTraitement = dateTraitement;
    }
    public LocalDateTime getDateTraitementFrom(){
        return this.dateTraitementFrom;
    }
    public void setDateTraitementFrom(LocalDateTime dateTraitementFrom){
        this.dateTraitementFrom = dateTraitementFrom;
    }
    public LocalDateTime getDateTraitementTo(){
        return this.dateTraitementTo;
    }
    public void setDateTraitementTo(LocalDateTime dateTraitementTo){
        this.dateTraitementTo = dateTraitementTo;
    }
    public Boolean getEnAttenteRetourClient(){
        return this.enAttenteRetourClient;
    }
    public void setEnAttenteRetourClient(Boolean enAttenteRetourClient){
        this.enAttenteRetourClient = enAttenteRetourClient;
    }

    public MotifDemandeCriteria getMotifDemande(){
        return this.motifDemande;
    }

    public void setMotifDemande(MotifDemandeCriteria motifDemande){
        this.motifDemande = motifDemande;
    }
    public List<MotifDemandeCriteria> getMotifDemandes(){
        return this.motifDemandes;
    }

    public void setMotifDemandes(List<MotifDemandeCriteria> motifDemandes){
        this.motifDemandes = motifDemandes;
    }
    public ActionDemandeCriteria getActionDemande(){
        return this.actionDemande;
    }

    public void setActionDemande(ActionDemandeCriteria actionDemande){
        this.actionDemande = actionDemande;
    }
    public List<ActionDemandeCriteria> getActionDemandes(){
        return this.actionDemandes;
    }

    public void setActionDemandes(List<ActionDemandeCriteria> actionDemandes){
        this.actionDemandes = actionDemandes;
    }
    public ClientCriteria getClient(){
        return this.client;
    }

    public void setClient(ClientCriteria client){
        this.client = client;
    }
    public List<ClientCriteria> getClients(){
        return this.clients;
    }

    public void setClients(List<ClientCriteria> clients){
        this.clients = clients;
    }
}
