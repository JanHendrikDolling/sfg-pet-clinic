package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.Owner;
import it.dolling.tryout.sfgpetclinic.model.Pet;
import it.dolling.tryout.sfgpetclinic.services.ContactInformationService;
import it.dolling.tryout.sfgpetclinic.services.OwnerService;
import it.dolling.tryout.sfgpetclinic.services.PetService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final ContactInformationService contactInformationService;

    public OwnerServiceMap(PetService petService, ContactInformationService contactInformationService) {
        this.petService = petService;
        this.contactInformationService = contactInformationService;
    }

    @Override
    public Owner save(Owner owner) {
        Objects.requireNonNull(owner, "can't save null object");
        if (owner.getPets() != null) {
            Set<Pet> pets = new HashSet<>();
            owner.getPets().forEach(pet -> {
                if (pet.getId() == null) {
                    pets.add(petService.save(pet));
                }
            });
            owner.setPets(pets);
        }
        if (owner.getContactInformation() != null) {
            owner.setContactInformation(contactInformationService.save(owner.getContactInformation()));
        }
        return super.save(owner);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return findAll().stream().filter(entry -> entry.getLastName().equals(lastName)).findFirst().orElseThrow(NoSuchElementException::new);
    }
}
