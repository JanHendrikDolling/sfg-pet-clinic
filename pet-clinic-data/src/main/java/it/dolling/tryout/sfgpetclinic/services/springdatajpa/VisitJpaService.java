package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.Visit;
import it.dolling.tryout.sfgpetclinic.repositories.VisitRepository;
import it.dolling.tryout.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class VisitJpaService extends AbstractJpaService<Visit, Long> implements VisitService {

    public VisitJpaService(VisitRepository visitRepository) {
        super(visitRepository);
    }

    @Override
    public Visit save(Visit visit) {
        if(visit.isInvalid()){
            throw new IllegalArgumentException("Visit is invalid.");
        }
        return super.save(visit);
    }

}
