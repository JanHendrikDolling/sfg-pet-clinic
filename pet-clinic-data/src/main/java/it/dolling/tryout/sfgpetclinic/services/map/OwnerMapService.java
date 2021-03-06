package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.Owner;
import it.dolling.tryout.sfgpetclinic.model.Pet;
import it.dolling.tryout.sfgpetclinic.services.ContactInformationService;
import it.dolling.tryout.sfgpetclinic.services.OwnerService;
import it.dolling.tryout.sfgpetclinic.services.PetService;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.Validate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetService petService;
    private final ContactInformationService contactInformationService;

    public OwnerMapService(PetService petService, ContactInformationService contactInformationService) {
        this.petService = petService;
        this.contactInformationService = contactInformationService;
    }

    @Override
    public Owner save(Owner owner) {
        Validate.notNull(owner, "can't save null object");
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

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        throw new NotImplementedException("has to be done");
    }
}
