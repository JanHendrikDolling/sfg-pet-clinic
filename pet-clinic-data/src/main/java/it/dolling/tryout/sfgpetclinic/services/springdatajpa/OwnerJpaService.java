package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.ContactInformation;
import it.dolling.tryout.sfgpetclinic.model.Owner;
import it.dolling.tryout.sfgpetclinic.repositories.OwnerRepository;
import it.dolling.tryout.sfgpetclinic.services.ContactInformationService;
import it.dolling.tryout.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdatajpa")
public class OwnerJpaService extends AbstractJpaService<Owner, Long> implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final ContactInformationService contactInformationService;

    public OwnerJpaService(OwnerRepository ownerRepository, ContactInformationService contactInformationService) {
        super(ownerRepository);
        this.ownerRepository = ownerRepository;
        this.contactInformationService = contactInformationService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Owner save(Owner owner) {
        if( owner.getContactInformation() != null && owner.getContactInformation().isNew()){
            ContactInformation contactInformation = contactInformationService.save(owner.getContactInformation());
            owner.setContactInformation(contactInformation);
        }

        return super.save(owner);
    }

}
