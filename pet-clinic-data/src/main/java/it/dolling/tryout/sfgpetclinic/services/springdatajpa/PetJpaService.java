package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.Pet;
import it.dolling.tryout.sfgpetclinic.repositories.PetRepository;
import it.dolling.tryout.sfgpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class PetJpaService extends AbstractJpaService<Pet, Long> implements PetService {
    public PetJpaService(PetRepository petRepository) {
        super(petRepository);
    }
}
