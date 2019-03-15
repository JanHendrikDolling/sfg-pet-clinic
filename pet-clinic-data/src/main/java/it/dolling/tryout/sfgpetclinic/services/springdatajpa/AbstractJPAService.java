package it.dolling.tryout.sfgpetclinic.services.springdatajpa;

import it.dolling.tryout.sfgpetclinic.model.BaseEntity;
import it.dolling.tryout.sfgpetclinic.services.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractJPAService<T extends BaseEntity, I extends Long> implements CrudService<T, I> {

    private final CrudRepository<T, I> repository;

    public AbstractJPAService(CrudRepository<T, I> repository) {
        this.repository = repository;
    }

    @Override
    public Set<T> findAll() {
        Set<T> all = new HashSet<>();
        repository.findAll().forEach(all::add);
        return all;
    }

    @Override
    public T findById(I id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public T save(T object) {
        return repository.save(object);
    }

    @Override
    public void delete(T object) {
        repository.delete(object);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
