package ma.zyn.app.unit.dao.facade.core.commun;

import ma.zyn.app.bean.core.commun.ActionDemande;
import ma.zyn.app.dao.facade.core.commun.ActionDemandeDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class ActionDemandeDaoTest {

@Autowired
    private ActionDemandeDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        ActionDemande entity = new ActionDemande();
        entity.setCode(code);
        underTest.save(entity);
        ActionDemande loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        ActionDemande loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        ActionDemande entity = new ActionDemande();
        entity.setId(id);
        underTest.save(entity);
        ActionDemande loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        ActionDemande entity = new ActionDemande();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        ActionDemande loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<ActionDemande> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<ActionDemande> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        ActionDemande given = constructSample(1);
        ActionDemande saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
