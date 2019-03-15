package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.Address;
import it.dolling.tryout.sfgpetclinic.services.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressMapService extends AbstractMapService<Address, Long> implements AddressService {
}
