package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.Address;
import it.dolling.tryout.sfgpetclinic.model.ContactInformation;
import it.dolling.tryout.sfgpetclinic.repositories.ContactInformationRepository;
import it.dolling.tryout.sfgpetclinic.services.AddressService;
import it.dolling.tryout.sfgpetclinic.services.ContactInformationService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class ContactInformationJpaService extends AbstractJpaService<ContactInformation, Long> implements ContactInformationService {

    private final AddressService addressService;

    public ContactInformationJpaService(ContactInformationRepository contactInformationRepository, AddressService addressService) {
        super(contactInformationRepository);
        this.addressService = addressService;
    }

    @Override
    public ContactInformation save(ContactInformation contactInformation) {
        if(contactInformation.getAddress() != null && contactInformation.getAddress().isNew()){
            Address address = addressService.save(contactInformation.getAddress());
            contactInformation.setAddress(address);
        }
        return super.save(contactInformation);
    }

}
