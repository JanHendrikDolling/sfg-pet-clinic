package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.Address;
import it.dolling.tryout.sfgpetclinic.services.AddressService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"default", "map"})
public class AddressMapService extends AbstractMapService<Address, Long> implements AddressService {
}
