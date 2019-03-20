package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.ContactInformation;
import it.dolling.tryout.sfgpetclinic.model.Owner;
import it.dolling.tryout.sfgpetclinic.model.Pet;
import it.dolling.tryout.sfgpetclinic.services.ContactInformationService;
import it.dolling.tryout.sfgpetclinic.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class OwnerMapServiceTest {
    private static final String LAST_NAME = "lastName";
    private static final String FIST_NAME = "fistName";

    @Mock
    PetService petService;

    @Mock
    ContactInformationService contactInformationService;

    @InjectMocks
    private OwnerMapService ownerMapService;

    private Owner.OwnerBuilder ownerBuilder;

    @BeforeEach
    void setUp(){
        // given
        ownerBuilder = Owner.builder().firstName(FIST_NAME).lastName(LAST_NAME);
    }

    @Test
    void save() {
        // given
        Owner owner = ownerBuilder.build();

        // when
        ownerMapService.save(owner);

        // then
        verifyNoMoreInteractions(petService);
        verifyNoMoreInteractions(contactInformationService);
        Set<Owner> allOwners = ownerMapService.findAll();
        assertThat(1, is(allOwners.size()));
        Optional<Owner> item = allOwners.stream().findFirst();
        if(item.isPresent()) {
            Owner savedOwner = item.get();
            assertThat(FIST_NAME, is(savedOwner.getFirstName()));
            assertThat(LAST_NAME, is(savedOwner.getLastName()));
        } else {
            fail("No Results saved.");
        }
    }

    @Test
    void saveWithContactInformation() {
        // given
        ContactInformation contactInformation = ContactInformation.builder().telephone("1234").build();
        Owner owner = ownerBuilder.contactInformation(contactInformation).build();

        // when
        ownerMapService.save(owner);

        // then
        verifyNoMoreInteractions(petService);
        verify(contactInformationService, times(1)).save(contactInformation);
        verifyNoMoreInteractions(contactInformationService);
    }

    @Test
    void saveWithPets() {
        // given
        Pet pet = Pet.builder().name("Pet").build();
        Set<Pet> pets = Stream.of(pet).collect(Collectors.toSet());
        Owner owner = ownerBuilder.pets(pets).build();

        // when
        ownerMapService.save(owner);

        // then
        verify(petService, times(1)).save(pet);
        verifyNoMoreInteractions(petService);
        verifyNoMoreInteractions(contactInformationService);
    }

    @Test
    void findByLastNameNoItem() {
        assertThrows(NoSuchElementException.class, () -> ownerMapService.findByLastName("Foo"));
    }

    @Test
    void findByLastName() {
        // given
        Owner owner = ownerBuilder.build();

        // when
        ownerMapService.save(owner);
        Owner resultOwner = ownerMapService.findByLastName(LAST_NAME);

        // then
        assertThat(owner, equalTo(resultOwner));
    }
}