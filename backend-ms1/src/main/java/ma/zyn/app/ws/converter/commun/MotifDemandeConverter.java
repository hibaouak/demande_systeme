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
import ma.zyn.app.bean.core.commun.MotifDemande;
import ma.zyn.app.ws.dto.commun.MotifDemandeDto;

@Component
public class MotifDemandeConverter {



    public MotifDemande toItem(MotifDemandeDto dto) {
        if (dto == null) {
            return null;
        } else {
        MotifDemande item = new MotifDemande();
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


    public MotifDemandeDto toDto(MotifDemande item) {
        if (item == null) {
            return null;
        } else {
            MotifDemandeDto dto = new MotifDemandeDto();
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


	
    public List<MotifDemande> toItem(List<MotifDemandeDto> dtos) {
        List<MotifDemande> items = new ArrayList<>();
        if (dtos != null && !dtos.isEmpty()) {
            for (MotifDemandeDto dto : dtos) {
                items.add(toItem(dto));
            }
        }
        return items;
    }


    public List<MotifDemandeDto> toDto(List<MotifDemande> items) {
        List<MotifDemandeDto> dtos = new ArrayList<>();
        if (items != null && !items.isEmpty()) {
            for (MotifDemande item : items) {
                dtos.add(toDto(item));
            }
        }
        return dtos;
    }


    public void copy(MotifDemandeDto dto, MotifDemande t) {
		BeanUtils.copyProperties(dto, t, AbstractConverterHelper.getNullPropertyNames(dto));
    }

    public List<MotifDemande> copy(List<MotifDemandeDto> dtos) {
        List<MotifDemande> result = new ArrayList<>();
        if (dtos != null) {
            for (MotifDemandeDto dto : dtos) {
                MotifDemande instance = new MotifDemande();
                copy(dto, instance);
                result.add(instance);
            }
        }
        return result.isEmpty() ? null : result;
    }


}
