package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.Visit;
import it.dolling.tryout.sfgpetclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

    @Override
    public Visit save(Visit visit){
        if(!visit.isValid()){
            throw new IllegalArgumentException("Visit is invalid.");
        }
        return super.save(visit);
    }

}
