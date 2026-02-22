package ma.zyn.app.unit.service.impl.admin.commun;

import ma.zyn.app.bean.core.commun.ActionDemande;
import ma.zyn.app.dao.facade.core.commun.ActionDemandeDao;
import ma.zyn.app.service.impl.admin.commun.ActionDemandeAdminServiceImpl;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class ActionDemandeAdminServiceImplTest {

    @Mock
    private ActionDemandeDao repository;
    private AutoCloseable autoCloseable;
    private ActionDemandeAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ActionDemandeAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllActionDemande() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveActionDemande() {
        // Given
        ActionDemande toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteActionDemande() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetActionDemandeById() {
        // Given
        Long idToRetrieve = 1L; // Example ActionDemande ID to retrieve
        ActionDemande expected = new ActionDemande(); // You need to replace ActionDemande with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        ActionDemande result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private ActionDemande constructSample(int i) {
		ActionDemande given = new ActionDemande();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setActif(false);
        return given;
    }

}
