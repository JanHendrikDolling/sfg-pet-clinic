package it.dolling.tryout.sfgpetclinic.repositories;

import it.dolling.tryout.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
