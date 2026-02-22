package  ma.zyn.app.ws.facade.admin.commun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.ArrayList;

import ma.zyn.app.bean.core.commun.ActionDemande;
import ma.zyn.app.dao.criteria.core.commun.ActionDemandeCriteria;
import ma.zyn.app.service.facade.admin.commun.ActionDemandeAdminService;
import ma.zyn.app.ws.converter.commun.ActionDemandeConverter;
import ma.zyn.app.ws.dto.commun.ActionDemandeDto;
import ma.zyn.app.zynerator.controller.AbstractController;
import ma.zyn.app.zynerator.dto.AuditEntityDto;
import ma.zyn.app.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zyn.app.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zyn.app.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/actionDemande/")
public class ActionDemandeRestAdmin {




    @Operation(summary = "Finds a list of all actionDemandes")
    @GetMapping("")
    public ResponseEntity<List<ActionDemandeDto>> findAll() throws Exception {
        ResponseEntity<List<ActionDemandeDto>> res = null;
        List<ActionDemande> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ActionDemandeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all actionDemandes")
    @GetMapping("optimized")
    public ResponseEntity<List<ActionDemandeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<ActionDemandeDto>> res = null;
        List<ActionDemande> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ActionDemandeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a actionDemande by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ActionDemandeDto> findById(@PathVariable Long id) {
        ActionDemande t = service.findById(id);
        if (t != null) {
            ActionDemandeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a actionDemande by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<ActionDemandeDto> findByLibelle(@PathVariable String libelle) {
	    ActionDemande t = service.findByReferenceEntity(new ActionDemande(libelle));
        if (t != null) {
            ActionDemandeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  actionDemande")
    @PostMapping("")
    public ResponseEntity<ActionDemandeDto> save(@RequestBody ActionDemandeDto dto) throws Exception {
        if(dto!=null){
            ActionDemande myT = converter.toItem(dto);
            ActionDemande t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                ActionDemandeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  actionDemande")
    @PutMapping("")
    public ResponseEntity<ActionDemandeDto> update(@RequestBody ActionDemandeDto dto) throws Exception {
        ResponseEntity<ActionDemandeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            ActionDemande t = service.findById(dto.getId());
            converter.copy(dto,t);
            ActionDemande updated = service.update(t);
            ActionDemandeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of actionDemande")
    @PostMapping("multiple")
    public ResponseEntity<List<ActionDemandeDto>> delete(@RequestBody List<ActionDemandeDto> dtos) throws Exception {
        ResponseEntity<List<ActionDemandeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<ActionDemande> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified actionDemande")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        ResponseEntity<Long> res;
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        if (id != null) {
            boolean resultDelete = service.deleteById(id);
            if (resultDelete) {
                status = HttpStatus.OK;
            }
        }
        res = new ResponseEntity<>(id, status);
        return res;
    }


    @Operation(summary = "Finds a actionDemande and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<ActionDemandeDto> findWithAssociatedLists(@PathVariable Long id) {
        ActionDemande loaded =  service.findWithAssociatedLists(id);
        ActionDemandeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds actionDemandes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ActionDemandeDto>> findByCriteria(@RequestBody ActionDemandeCriteria criteria) throws Exception {
        ResponseEntity<List<ActionDemandeDto>> res = null;
        List<ActionDemande> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<ActionDemandeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated actionDemandes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ActionDemandeCriteria criteria) throws Exception {
        List<ActionDemande> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<ActionDemandeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets actionDemande data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ActionDemandeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<ActionDemandeDto> findDtos(List<ActionDemande> list){
        List<ActionDemandeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<ActionDemandeDto> getDtoResponseEntity(ActionDemandeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public ActionDemandeRestAdmin(ActionDemandeAdminService service, ActionDemandeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final ActionDemandeAdminService service;
    private final ActionDemandeConverter converter;





}
