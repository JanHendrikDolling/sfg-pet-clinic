package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.Vet;
import it.dolling.tryout.sfgpetclinic.repositories.VetRepository;
import it.dolling.tryout.sfgpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VetJpaService extends AbstractJpaService<Vet, Long> implements VetService {
    public VetJpaService(VetRepository vetRepository) {
        super(vetRepository);
    }
}
