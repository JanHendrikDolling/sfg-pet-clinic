package it.dolling.tryout.sfgpetclinic.services;

import it.dolling.tryout.sfgpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
