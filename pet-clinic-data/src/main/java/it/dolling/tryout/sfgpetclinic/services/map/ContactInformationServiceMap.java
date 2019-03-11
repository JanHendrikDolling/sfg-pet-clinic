package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.ContactInformation;
import it.dolling.tryout.sfgpetclinic.services.ContactInformationservice;
import org.springframework.stereotype.Service;

@Service
public class ContactInformationServiceMap extends AbstractMapService<ContactInformation, Long> implements ContactInformationservice {
}
