package  ma.zyn.app.ws.converter.demande;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;

import ma.zyn.app.ws.converter.commun.ActionDemandeConverter;
import ma.zyn.app.bean.core.commun.ActionDemande;
import ma.zyn.app.ws.converter.commun.MotifDemandeConverter;
import ma.zyn.app.bean.core.commun.MotifDemande;
import ma.zyn.app.ws.converter.demande.ClientConverter;
import ma.zyn.app.bean.core.demande.Client;



import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.ws.dto.demande.DemandeDto;

@Component
public class DemandeConverter {

    @Autowired
    private ActionDemandeConverter actionDemandeConverter ;
    @Autowired
    private MotifDemandeConverter motifDemandeConverter ;
    @Autowired
    private ClientConverter clientConverter ;
    private boolean motifDemande;
    private boolean actionDemande;
    private boolean client;

    public  DemandeConverter() {
        initObject(true);
    }

    public Demande toItem(DemandeDto dto) {
        if (dto == null) {
            return null;
        } else {
        Demande item = new Demande();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getNumero()))
                item.setNumero(dto.getNumero());
            if(StringUtil.isNotEmpty(dto.getAdresse()))
                item.setAdresse(dto.getAdresse());
            if(StringUtil.isNotEmpty(dto.getDateAffectation()))
                item.setDateAffectation(DateUtil.stringEnToDate(dto.getDateAffectation()));
            if(StringUtil.isNotEmpty(dto.getDateAnnulation()))
                item.setDateAnnulation(DateUtil.stringEnToDate(dto.getDateAnnulation()));
            if(StringUtil.isNotEmpty(dto.getDateCloture()))
                item.setDateCloture(DateUtil.stringEnToDate(dto.getDateCloture()));
            if(StringUtil.isNotEmpty(dto.getDateTraitement()))
                item.setDateTraitement(DateUtil.stringEnToDate(dto.getDateTraitement()));
            if(dto.getEnAttenteRetourClient() != null)
                item.setEnAttenteRetourClient(dto.getEnAttenteRetourClient());
            if(this.motifDemande && dto.getMotifDemande()!=null)
                item.setMotifDemande(motifDemandeConverter.toItem(dto.getMotifDemande())) ;

            if(this.actionDemande && dto.getActionDemande()!=null)
                item.setActionDemande(actionDemandeConverter.toItem(dto.getActionDemande())) ;

            if(this.client && dto.getClient()!=null)
                item.setClient(clientConverter.toItem(dto.getClient())) ;




        return item;
        }
    }


    public DemandeDto toDto(Demande item) {
        if (item == null) {
            return null;
        } else {
            DemandeDto dto = new DemandeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getNumero()))
                dto.setNumero(item.getNumero());
            if(StringUtil.isNotEmpty(item.getAdresse()))
                dto.setAdresse(item.getAdresse());
            if(item.getDateAffectation()!=null)
                dto.setDateAffectation(DateUtil.dateTimeToString(item.getDateAffectation()));
            if(item.getDateAnnulation()!=null)
                dto.setDateAnnulation(DateUtil.dateTimeToString(item.getDateAnnulation()));
            if(item.getDateCloture()!=null)
                dto.setDateCloture(DateUtil.dateTimeToString(item.getDateCloture()));
            if(item.getDateTraitement()!=null)
                dto.setDateTraitement(DateUtil.dateTimeToString(item.getDateTraitement()));
                dto.setEnAttenteRetourClient(item.getEnAttenteRetourClient());
            if(this.motifDemande && item.getMotifDemande()!=null) {
                dto.setMotifDemande(motifDemandeConverter.toDto(item.getMotifDemande())) ;

            }
            if(this.actionDemande && item.getActionDemande()!=null) {
                dto.setActionDemande(actionDemandeConverter.toDto(item.getActionDemande())) ;

            }
            if(this.client && item.getClient()!=null) {
                dto.setClient(clientConverter.toDto(item.getClient())) ;

            }


        return dto;
        }
    }

    public void init(boolean value) {
        initObject(value);
    }

    public void initObject(boolean value) {
        this.motifDemande = value;
        this.actionDemande = value;
        this.client = value;
    }
	
    public List<Demande> toItem(List<DemandeDto> dtos) {
        List<Demande> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (DemandeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<DemandeDto> toDto(List<Demande> items) {
        List<DemandeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (Demande item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(DemandeDto dto, Demande t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
        if(t.getMotifDemande() == null  && dto.getMotifDemande() != null){
            t.setMotifDemande(new MotifDemande());
        }else if (t.getMotifDemande() != null  && dto.getMotifDemande() != null){
            t.setMotifDemande(null);
            t.setMotifDemande(new MotifDemande());
        }
        if(t.getActionDemande() == null  && dto.getActionDemande() != null){
            t.setActionDemande(new ActionDemande());
        }else if (t.getActionDemande() != null  && dto.getActionDemande() != null){
            t.setActionDemande(null);
            t.setActionDemande(new ActionDemande());
        }
        if(t.getClient() == null  && dto.getClient() != null){
            t.setClient(new Client());
        }else if (t.getClient() != null  && dto.getClient() != null){
            t.setClient(null);
            t.setClient(new Client());
        }
        if (dto.getMotifDemande() != null)
        motifDemandeConverter.copy(dto.getMotifDemande(), t.getMotifDemande());
        if (dto.getActionDemande() != null)
        actionDemandeConverter.copy(dto.getActionDemande(), t.getActionDemande());
        if (dto.getClient() != null)
        clientConverter.copy(dto.getClient(), t.getClient());
    }

    public List<Demande> copy(List<DemandeDto> dtos) {
        List<Demande> result = new ArrayList<>();
        if (dtos != null) {
            for (DemandeDto dto : dtos) {
                Demande instance = new Demande();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


    public ActionDemandeConverter getActionDemandeConverter(){
        return this.actionDemandeConverter;
    }
    public void setActionDemandeConverter(ActionDemandeConverter actionDemandeConverter ){
        this.actionDemandeConverter = actionDemandeConverter;
    }
    public MotifDemandeConverter getMotifDemandeConverter(){
        return this.motifDemandeConverter;
    }
    public void setMotifDemandeConverter(MotifDemandeConverter motifDemandeConverter ){
        this.motifDemandeConverter = motifDemandeConverter;
    }
    public ClientConverter getClientConverter(){
        return this.clientConverter;
    }
    public void setClientConverter(ClientConverter clientConverter ){
        this.clientConverter = clientConverter;
    }
    public boolean  isMotifDemande(){
        return this.motifDemande;
    }
    public void  setMotifDemande(boolean motifDemande){
        this.motifDemande = motifDemande;
    }
    public boolean  isActionDemande(){
        return this.actionDemande;
    }
    public void  setActionDemande(boolean actionDemande){
        this.actionDemande = actionDemande;
    }
    public boolean  isClient(){
        return this.client;
    }
    public void  setClient(boolean client){
        this.client = client;
    }
}
