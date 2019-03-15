package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.ContactInformation;
import it.dolling.tryout.sfgpetclinic.repositories.ContactInformationRepository;
import it.dolling.tryout.sfgpetclinic.services.ContactInformationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class ContactInformationJpaService extends AbstractJpaService<ContactInformation, Long> implements ContactInformationService {
    public ContactInformationJpaService(ContactInformationRepository contactInformationRepository) {
        super(contactInformationRepository);
    }
}
