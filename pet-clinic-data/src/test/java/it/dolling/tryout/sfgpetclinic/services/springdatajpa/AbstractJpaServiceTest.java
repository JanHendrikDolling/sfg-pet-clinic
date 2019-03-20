package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.BaseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AbstractJpaServiceTest {

    private BaseEntity baseEntity5;
    private BaseEntity baseEntity10;

    private static class AbstractJpaServiceImpl extends AbstractJpaService<BaseEntity, Long> {
        public AbstractJpaServiceImpl(CrudRepository<BaseEntity, Long> repository) {
            super(repository);
        }
    }

    @Mock
    CrudRepository<BaseEntity, Long> repository;

    @InjectMocks
    private AbstractJpaServiceImpl service;

    @BeforeEach
    void setUp() {
        baseEntity5 = new BaseEntity(5L);
        baseEntity10 = new BaseEntity(10L);
    }

    @Test
    void findAll() {
        // given
        when(repository.findAll()).thenReturn(Stream.of(baseEntity5, baseEntity10).collect(Collectors.toSet()));

        // when
        Set<BaseEntity> baseEntities = service.findAll();

        // then
        assertThat(2, is(baseEntities.size()));
        assertThat(baseEntities, containsInAnyOrder(baseEntity5, baseEntity10));
        verify(repository, times(1)).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findById() {
        // given
        when(repository.findById(baseEntity5.getId())).thenReturn(Optional.of(baseEntity5));

        // when
        BaseEntity result = service.findById(baseEntity5.getId());

        // then
        assertThat(baseEntity5, equalTo(result));
        verify(repository, times(1)).findById(baseEntity5.getId());
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findByIdNotFound() {
        // given
        long id = 392L;
        when(repository.findById(any())).thenReturn(Optional.empty());

        // when
        BaseEntity result = service.findById(id);

        // then
        assertNull(result);
        verify(repository, times(1)).findById(id);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void save() {
        // given
        when(repository.save(baseEntity5)).thenReturn(baseEntity5);

        // when
        BaseEntity savedEntity = service.save(baseEntity5);

        // then
        assertThat(baseEntity5, equalTo(savedEntity));
        verify(repository, times(1)).save(baseEntity5);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {
        // when
        service.delete(baseEntity5);

        // then
        verify(repository, times(1)).delete(baseEntity5);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteAll() {
        // when
        service.deleteAll();

        // then
        verify(repository, times(1)).deleteAll();
        verifyNoMoreInteractions(repository);
    }
}