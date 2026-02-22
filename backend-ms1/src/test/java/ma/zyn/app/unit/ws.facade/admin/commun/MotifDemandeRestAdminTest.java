package ma.zyn.app.unit.ws.facade.admin.commun;

import ma.zyn.app.bean.core.commun.MotifDemande;
import ma.zyn.app.service.impl.admin.commun.MotifDemandeAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.commun.MotifDemandeRestAdmin;
import ma.zyn.app.ws.converter.commun.MotifDemandeConverter;
import ma.zyn.app.ws.dto.commun.MotifDemandeDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MotifDemandeRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private MotifDemandeAdminServiceImpl service;
    @Mock
    private MotifDemandeConverter converter;

    @InjectMocks
    private MotifDemandeRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllMotifDemandeTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<MotifDemandeDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<MotifDemandeDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveMotifDemandeTest() throws Exception {
        // Mock data
        MotifDemandeDto requestDto = new MotifDemandeDto();
        MotifDemande entity = new MotifDemande();
        MotifDemande saved = new MotifDemande();
        MotifDemandeDto savedDto = new MotifDemandeDto();

        // Mock the converter to return the motifDemande object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved motifDemande DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<MotifDemandeDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        MotifDemandeDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved motifDemande DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getActif(), responseBody.getActif());
    }

}
