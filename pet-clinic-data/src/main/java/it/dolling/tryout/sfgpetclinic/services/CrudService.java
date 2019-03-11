package it.dolling.tryout.sfgpetclinic.services;

import it.dolling.tryout.sfgpetclinic.model.BaseEntity;

import java.util.Set;

public interface CrudService<T extends BaseEntity, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteAll();
}
