package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.Speciality;
import it.dolling.tryout.sfgpetclinic.repositories.SpecialtyRepository;
import it.dolling.tryout.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class SpecialityJpaService extends AbstractJpaService<Speciality, Long> implements SpecialityService {
    public SpecialityJpaService(SpecialtyRepository specialtyRepository) {
        super(specialtyRepository);
    }
}
