package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.Pet;
import it.dolling.tryout.sfgpetclinic.services.PetService;
import it.dolling.tryout.sfgpetclinic.services.PetTypeService;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PetMapService extends AbstractMapService<Pet, Long> implements PetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PetMapService.class);

    private final PetTypeService petTypeService;

    public PetMapService(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public Pet save(Pet pet) {
        Validate.notNull(pet, "can't save null object");
        if (pet.getPetType() != null) {
            pet.setPetType(petTypeService.save(pet.getPetType()));
        }
        Pet savedPet = super.save(pet);
        if (savedPet.getOwner() != null && savedPet.getOwner().getId() == null) {
            LOGGER.warn("Owner {} not persisted!", savedPet.getOwner());
        }
        return pet;
    }
}
