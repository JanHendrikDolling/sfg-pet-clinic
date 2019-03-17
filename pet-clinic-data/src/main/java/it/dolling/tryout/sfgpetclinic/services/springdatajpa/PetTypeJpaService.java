package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.PetType;
import it.dolling.tryout.sfgpetclinic.repositories.PetTypeRepository;
import it.dolling.tryout.sfgpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class PetTypeJpaService extends AbstractJpaService<PetType, Long> implements PetTypeService {
    public PetTypeJpaService(PetTypeRepository petTypeRepository) {
        super(petTypeRepository);
    }
}
