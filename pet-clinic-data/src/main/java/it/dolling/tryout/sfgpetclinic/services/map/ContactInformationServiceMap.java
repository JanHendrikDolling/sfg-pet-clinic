package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.ContactInformation;
import it.dolling.tryout.sfgpetclinic.services.AddressService;
import it.dolling.tryout.sfgpetclinic.services.ContactInformationService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ContactInformationServiceMap extends AbstractMapService<ContactInformation, Long> implements ContactInformationService {

    private final AddressService addressService;

    public ContactInformationServiceMap(AddressService addressService) {
        this.addressService = addressService;
    }


    @Override
    public ContactInformation save(ContactInformation contactInformation) {
        Objects.requireNonNull(contactInformation, "can't save null object");
        if (contactInformation.getAddress() != null) {
            contactInformation.setAddress(addressService.save(contactInformation.getAddress()));
        }
        return super.save(contactInformation);
    }
}
