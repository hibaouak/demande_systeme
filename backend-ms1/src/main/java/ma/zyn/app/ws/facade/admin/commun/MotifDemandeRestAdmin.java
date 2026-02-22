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

import ma.zyn.app.bean.core.commun.MotifDemande;
import ma.zyn.app.dao.criteria.core.commun.MotifDemandeCriteria;
import ma.zyn.app.service.facade.admin.commun.MotifDemandeAdminService;
import ma.zyn.app.ws.converter.commun.MotifDemandeConverter;
import ma.zyn.app.ws.dto.commun.MotifDemandeDto;
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
@RequestMapping("/api/admin/motifDemande/")
public class MotifDemandeRestAdmin {




    @Operation(summary = "Finds a list of all motifDemandes")
    @GetMapping("")
    public ResponseEntity<List<MotifDemandeDto>> findAll() throws Exception {
        ResponseEntity<List<MotifDemandeDto>> res = null;
        List<MotifDemande> list = service.findAll();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MotifDemandeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds an optimized list of all motifDemandes")
    @GetMapping("optimized")
    public ResponseEntity<List<MotifDemandeDto>> findAllOptimized() throws Exception {
        ResponseEntity<List<MotifDemandeDto>> res = null;
        List<MotifDemande> list = service.findAllOptimized();
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MotifDemandeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds a motifDemande by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MotifDemandeDto> findById(@PathVariable Long id) {
        MotifDemande t = service.findById(id);
        if (t != null) {
            MotifDemandeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Finds a motifDemande by libelle")
    @GetMapping("libelle/{libelle}")
    public ResponseEntity<MotifDemandeDto> findByLibelle(@PathVariable String libelle) {
	    MotifDemande t = service.findByReferenceEntity(new MotifDemande(libelle));
        if (t != null) {
            MotifDemandeDto dto = converter.toDto(t);
            return getDtoResponseEntity(dto);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Saves the specified  motifDemande")
    @PostMapping("")
    public ResponseEntity<MotifDemandeDto> save(@RequestBody MotifDemandeDto dto) throws Exception {
        if(dto!=null){
            MotifDemande myT = converter.toItem(dto);
            MotifDemande t = service.create(myT);
            if (t == null) {
                return new ResponseEntity<>(null, HttpStatus.IM_USED);
            }else{
                MotifDemandeDto myDto = converter.toDto(t);
                return new ResponseEntity<>(myDto, HttpStatus.CREATED);
            }
        }else {
            return new ResponseEntity<>(dto, HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Updates the specified  motifDemande")
    @PutMapping("")
    public ResponseEntity<MotifDemandeDto> update(@RequestBody MotifDemandeDto dto) throws Exception {
        ResponseEntity<MotifDemandeDto> res ;
        if (dto.getId() == null || service.findById(dto.getId()) == null)
            res = new ResponseEntity<>(HttpStatus.CONFLICT);
        else {
            MotifDemande t = service.findById(dto.getId());
            converter.copy(dto,t);
            MotifDemande updated = service.update(t);
            MotifDemandeDto myDto = converter.toDto(updated);
            res = new ResponseEntity<>(myDto, HttpStatus.OK);
        }
        return res;
    }

    @Operation(summary = "Delete list of motifDemande")
    @PostMapping("multiple")
    public ResponseEntity<List<MotifDemandeDto>> delete(@RequestBody List<MotifDemandeDto> dtos) throws Exception {
        ResponseEntity<List<MotifDemandeDto>> res ;
        HttpStatus status = HttpStatus.CONFLICT;
        if (dtos != null && !dtos.isEmpty()) {
            List<MotifDemande> ts = converter.toItem(dtos);
            service.delete(ts);
            status = HttpStatus.OK;
        }
        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Delete the specified motifDemande")
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


    @Operation(summary = "Finds a motifDemande and associated list by id")
    @GetMapping("detail/id/{id}")
    public ResponseEntity<MotifDemandeDto> findWithAssociatedLists(@PathVariable Long id) {
        MotifDemande loaded =  service.findWithAssociatedLists(id);
        MotifDemandeDto dto = converter.toDto(loaded);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Finds motifDemandes by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<MotifDemandeDto>> findByCriteria(@RequestBody MotifDemandeCriteria criteria) throws Exception {
        ResponseEntity<List<MotifDemandeDto>> res = null;
        List<MotifDemande> list = service.findByCriteria(criteria);
        HttpStatus status = HttpStatus.NO_CONTENT;
        List<MotifDemandeDto> dtos  = converter.toDto(list);
        if (dtos != null && !dtos.isEmpty())
            status = HttpStatus.OK;

        res = new ResponseEntity<>(dtos, status);
        return res;
    }

    @Operation(summary = "Finds paginated motifDemandes by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody MotifDemandeCriteria criteria) throws Exception {
        List<MotifDemande> list = service.findPaginatedByCriteria(criteria, criteria.getPage(), criteria.getMaxResults(), criteria.getSortOrder(), criteria.getSortField());
        List<MotifDemandeDto> dtos = converter.toDto(list);
        PaginatedList paginatedList = new PaginatedList();
        paginatedList.setList(dtos);
        if (dtos != null && !dtos.isEmpty()) {
            int dateSize = service.getDataSize(criteria);
            paginatedList.setDataSize(dateSize);
        }
        return new ResponseEntity<>(paginatedList, HttpStatus.OK);
    }

    @Operation(summary = "Gets motifDemande data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody MotifDemandeCriteria criteria) throws Exception {
        int count = service.getDataSize(criteria);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
	
	public List<MotifDemandeDto> findDtos(List<MotifDemande> list){
        List<MotifDemandeDto> dtos = converter.toDto(list);
        return dtos;
    }

    private ResponseEntity<MotifDemandeDto> getDtoResponseEntity(MotifDemandeDto dto) {
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }






   public MotifDemandeRestAdmin(MotifDemandeAdminService service, MotifDemandeConverter converter){
        this.service = service;
        this.converter = converter;
    }

    private final MotifDemandeAdminService service;
    private final MotifDemandeConverter converter;





}
