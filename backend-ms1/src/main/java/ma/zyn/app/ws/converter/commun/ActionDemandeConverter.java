package  ma.zyn.app.ws.converter.commun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.BeanUtils;
import ma.zyn.app.zynerator.converter.AbstractConverterHelper;

import java.util.ArrayList;
import java.util.List;




import ma.zyn.app.zynerator.util.StringUtil;
import ma.zyn.app.zynerator.converter.AbstractConverter;
import ma.zyn.app.zynerator.util.DateUtil;
import ma.zyn.app.bean.core.commun.ActionDemande;
import ma.zyn.app.ws.dto.commun.ActionDemandeDto;

@Component
public class ActionDemandeConverter {



    public ActionDemande toItem(ActionDemandeDto dto) {
        if (dto == null) {
            return null;
        } else {
        ActionDemande item = new ActionDemande();
            if(StringUtil.isNotEmpty(dto.getId()))
                item.setId(dto.getId());
            if(StringUtil.isNotEmpty(dto.getCode()))
                item.setCode(dto.getCode());
            if(StringUtil.isNotEmpty(dto.getLibelle()))
                item.setLibelle(dto.getLibelle());
            if(StringUtil.isNotEmpty(dto.getDescription()))
                item.setDescription(dto.getDescription());
            if(dto.getActif() != null)
                item.setActif(dto.getActif());



        return item;
        }
    }


    public ActionDemandeDto toDto(ActionDemande item) {
        if (item == null) {
            return null;
        } else {
            ActionDemandeDto dto = new ActionDemandeDto();
            if(StringUtil.isNotEmpty(item.getId()))
                dto.setId(item.getId());
            if(StringUtil.isNotEmpty(item.getCode()))
                dto.setCode(item.getCode());
            if(StringUtil.isNotEmpty(item.getLibelle()))
                dto.setLibelle(item.getLibelle());
            if(StringUtil.isNotEmpty(item.getDescription()))
                dto.setDescription(item.getDescription());
                dto.setActif(item.getActif());


        return dto;
        }
    }


	
    public List<ActionDemande> toItem(List<ActionDemandeDto> dtos) {
        List<ActionDemande> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (ActionDemandeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<ActionDemandeDto> toDto(List<ActionDemande> items) {
        List<ActionDemandeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (ActionDemande item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(ActionDemandeDto dto, ActionDemande t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<ActionDemande> copy(List<ActionDemandeDto> dtos) {
        List<ActionDemande> result = new ArrayList<>();
        if (dtos != null) {
            for (ActionDemandeDto dto : dtos) {
                ActionDemande instance = new ActionDemande();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
