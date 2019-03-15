package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.Address;
import it.dolling.tryout.sfgpetclinic.repositories.AddressRepository;
import it.dolling.tryout.sfgpetclinic.services.AddressService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("springdatajpa")
public class AddressJpaService extends AbstractJpaService<Address, Long> implements AddressService {
    public AddressJpaService(AddressRepository addressRepository) {
        super(addressRepository);
    }
}
