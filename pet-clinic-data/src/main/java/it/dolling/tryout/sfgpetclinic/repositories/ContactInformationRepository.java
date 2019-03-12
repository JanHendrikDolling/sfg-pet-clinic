package it.dolling.tryout.sfgpetclinic.repositories;

import it.dolling.tryout.sfgpetclinic.model.ContactInformation;
import org.springframework.data.repository.CrudRepository;

public interface ContactInformationRepository extends CrudRepository<ContactInformation, Long> {
}
