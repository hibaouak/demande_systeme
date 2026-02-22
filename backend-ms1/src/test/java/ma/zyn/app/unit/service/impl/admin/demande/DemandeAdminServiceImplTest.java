package ma.zyn.app.unit.service.impl.admin.demande;

import ma.zyn.app.bean.core.demande.Demande;
import ma.zyn.app.dao.facade.core.demande.DemandeDao;
import ma.zyn.app.service.impl.admin.demande.DemandeAdminServiceImpl;

import ma.zyn.app.bean.core.commun.ActionDemande ;
import ma.zyn.app.bean.core.commun.MotifDemande ;
import ma.zyn.app.bean.core.demande.Client ;
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
class DemandeAdminServiceImplTest {

    @Mock
    private DemandeDao repository;
    private AutoCloseable autoCloseable;
    private DemandeAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DemandeAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllDemande() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveDemande() {
        // Given
        Demande toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteDemande() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetDemandeById() {
        // Given
        Long idToRetrieve = 1L; // Example Demande ID to retrieve
        Demande expected = new Demande(); // You need to replace Demande with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Demande result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Demande constructSample(int i) {
		Demande given = new Demande();
        given.setMotifDemande(new MotifDemande(1L));
        given.setActionDemande(new ActionDemande(1L));
        given.setNumero("numero-"+i);
        given.setAdresse("adresse-"+i);
        given.setDateAffectation(LocalDateTime.now());
        given.setDateAnnulation(LocalDateTime.now());
        given.setDateCloture(LocalDateTime.now());
        given.setDateTraitement(LocalDateTime.now());
        given.setEnAttenteRetourClient(false);
        given.setClient(new Client(1L));
        return given;
    }

}
