package  ma.zyn.app.ws.dto.demande;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;





@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDto  extends AuditBaseDto {

    private String code  ;
    private String libelle  ;
    private String description  ;
    private Boolean actif  ;




    public ClientDto(){
        super();
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


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public Boolean getActif(){
        return this.actif;
    }
    public void setActif(Boolean actif){
        this.actif = actif;
    }








}
