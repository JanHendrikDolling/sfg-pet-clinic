package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.Speciality;
import it.dolling.tryout.sfgpetclinic.model.Vet;
import it.dolling.tryout.sfgpetclinic.services.SpecialityService;
import it.dolling.tryout.sfgpetclinic.services.VetService;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet save(Vet vet) {
        Validate.notNull(vet, "can't save null object");
        if (vet.getSpecialities() != null) {
            Set<Speciality> specialities = new HashSet<>();
            vet.getSpecialities().forEach(speciality -> {
                if (speciality.getId() == null) {
                    specialities.add(specialityService.save(speciality));
                }
            });
            vet.setSpecialities(specialities);
        }
        return super.save(vet);
    }

}
