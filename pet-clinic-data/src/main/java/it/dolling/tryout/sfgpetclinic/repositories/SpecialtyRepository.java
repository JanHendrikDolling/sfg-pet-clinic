package it.dolling.tryout.sfgpetclinic.repositories;

import it.dolling.tryout.sfgpetclinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {
}
