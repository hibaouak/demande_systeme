package ma.zyn.app.unit.service.impl.admin.commun;

import ma.zyn.app.bean.core.commun.MotifDemande;
import ma.zyn.app.dao.facade.core.commun.MotifDemandeDao;
import ma.zyn.app.service.impl.admin.commun.MotifDemandeAdminServiceImpl;

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
class MotifDemandeAdminServiceImplTest {

    @Mock
    private MotifDemandeDao repository;
    private AutoCloseable autoCloseable;
    private MotifDemandeAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new MotifDemandeAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllMotifDemande() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveMotifDemande() {
        // Given
        MotifDemande toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteMotifDemande() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetMotifDemandeById() {
        // Given
        Long idToRetrieve = 1L; // Example MotifDemande ID to retrieve
        MotifDemande expected = new MotifDemande(); // You need to replace MotifDemande with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        MotifDemande result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private MotifDemande constructSample(int i) {
		MotifDemande given = new MotifDemande();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setDescription("description-"+i);
        given.setActif(false);
        return given;
    }

}
