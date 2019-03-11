package it.dolling.tryout.sfgpetclinic.services;

import it.dolling.tryout.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
