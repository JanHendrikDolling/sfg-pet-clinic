package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.ContactInformation;
import it.dolling.tryout.sfgpetclinic.services.AddressService;
import it.dolling.tryout.sfgpetclinic.services.ContactInformationService;
import org.apache.commons.lang3.Validate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class ContactInformationMapService extends AbstractMapService<ContactInformation, Long> implements ContactInformationService {

    private final AddressService addressService;

    public ContactInformationMapService(AddressService addressService) {
        this.addressService = addressService;
    }


    @Override
    public ContactInformation save(ContactInformation contactInformation) {
        Validate.notNull(contactInformation, "can't save null object");
        if (contactInformation.getAddress() != null) {
            contactInformation.setAddress(addressService.save(contactInformation.getAddress()));
        }
        return super.save(contactInformation);
    }
}
