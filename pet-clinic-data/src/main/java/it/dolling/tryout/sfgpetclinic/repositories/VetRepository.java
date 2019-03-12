package it.dolling.tryout.sfgpetclinic.repositories;

import it.dolling.tryout.sfgpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
