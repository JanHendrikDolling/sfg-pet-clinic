package it.dolling.tryout.sfgpetclinic.repositories;

import it.dolling.tryout.sfgpetclinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}
