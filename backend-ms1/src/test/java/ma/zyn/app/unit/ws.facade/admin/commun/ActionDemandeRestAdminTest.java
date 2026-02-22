package ma.zyn.app.unit.ws.facade.admin.commun;

import ma.zyn.app.bean.core.commun.ActionDemande;
import ma.zyn.app.service.impl.admin.commun.ActionDemandeAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.commun.ActionDemandeRestAdmin;
import ma.zyn.app.ws.converter.commun.ActionDemandeConverter;
import ma.zyn.app.ws.dto.commun.ActionDemandeDto;
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
public class ActionDemandeRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private ActionDemandeAdminServiceImpl service;
    @Mock
    private ActionDemandeConverter converter;

    @InjectMocks
    private ActionDemandeRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllActionDemandeTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<ActionDemandeDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<ActionDemandeDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveActionDemandeTest() throws Exception {
        // Mock data
        ActionDemandeDto requestDto = new ActionDemandeDto();
        ActionDemande entity = new ActionDemande();
        ActionDemande saved = new ActionDemande();
        ActionDemandeDto savedDto = new ActionDemandeDto();

        // Mock the converter to return the actionDemande object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved actionDemande DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<ActionDemandeDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        ActionDemandeDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved actionDemande DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
        assertEquals(savedDto.getActif(), responseBody.getActif());
    }

}
