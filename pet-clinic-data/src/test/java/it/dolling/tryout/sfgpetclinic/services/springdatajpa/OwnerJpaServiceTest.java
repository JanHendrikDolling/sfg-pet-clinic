package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.Owner;
import it.dolling.tryout.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {
    private static final String LAST_NAME = "Smith";

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerJpaService ownerJpaService;

    @Test
    void findByLastName() {
        // given
        Owner returnOwner = Owner.builder().lastName(LAST_NAME).build();
        when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(returnOwner);

        // when
        Owner smith = ownerJpaService.findByLastName(LAST_NAME);

        // then
        assertThat(smith, equalTo(returnOwner));
        verify(ownerRepository, times(1)).findByLastName(LAST_NAME);
    }
}