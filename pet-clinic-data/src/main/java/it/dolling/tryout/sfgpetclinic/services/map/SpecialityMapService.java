package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.Speciality;
import it.dolling.tryout.sfgpetclinic.services.SpecialityService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class SpecialityMapService extends AbstractMapService<Speciality, Long> implements SpecialityService {

}
