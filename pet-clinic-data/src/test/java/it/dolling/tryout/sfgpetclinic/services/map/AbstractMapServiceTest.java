package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.BaseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractMapServiceTest {

    private class AbstractMapServiceImpl extends AbstractMapService<BaseEntity, Long> {
    }

    private AbstractMapServiceImpl abstractMapService;


    @BeforeEach
    void setUp() {
        abstractMapService = new AbstractMapServiceImpl();
    }

    @Test
    void findAll() {
        // given
        abstractMapService.save(new BaseEntity());

        // when
        Set<BaseEntity> all = abstractMapService.findAll();

        // then
        assertThat(1, is(all.size()));
    }

    @Test
    void findById() {
        // given
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setId(10L);
        abstractMapService.save(baseEntity);

        // when
        BaseEntity byId = abstractMapService.findById(10L);

        // then
        assertThat(baseEntity, equalTo(byId));
    }

    @Test
    void save() {
        // when
        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () ->  abstractMapService.save(null)
        );

        // then
        assertThat(exception.getMessage(), is("can't save null object"));
    }

    @Test
    void deleteById() {
        // given
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setId(10L);
        abstractMapService.save(baseEntity);

        // when
        abstractMapService.deleteById(10L);

        // then
        Set<BaseEntity> all = abstractMapService.findAll();
        assertThat(0, is(all.size()));
    }

    @Test
    void delete() {
        // given
        BaseEntity baseEntity = abstractMapService.save(new BaseEntity());

        // when
        abstractMapService.delete(baseEntity);

        // then
        Set<BaseEntity> all = abstractMapService.findAll();
        assertThat(0, is(all.size()));
    }

    @Test
    void deleteAll() {
        // given
        abstractMapService.save(new BaseEntity());

        // when
        abstractMapService.deleteAll();

        // then
        Set<BaseEntity> all = abstractMapService.findAll();
        assertThat(0, is(all.size()));
    }
}