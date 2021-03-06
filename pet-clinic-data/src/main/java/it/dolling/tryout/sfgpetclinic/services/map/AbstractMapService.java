package it.dolling.tryout.sfgpetclinic.services.map;

import it.dolling.tryout.sfgpetclinic.model.BaseEntity;
import it.dolling.tryout.sfgpetclinic.services.CrudService;
import org.apache.commons.lang3.Validate;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity, I extends Long> implements CrudService<T, I> {

    private Map<Long, T> map = new HashMap<>();

    @Override
    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public T findById(I id) {
        return map.get(id);
    }

    @Override
    public T save(T object) {
        Validate.notNull(object, "can't save null object");
        if (object.getId() == null) {
            object.setId(getNextId());
        }
        map.put(object.getId(), object);
        return object;
    }

    void deleteById(I id) {
        map.remove(id);
    }

    @Override
    public void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    @Override
    public void deleteAll() {
        map.clear();
    }


    private Long getNextId() {
        if (map.keySet().isEmpty()) {
            return 1L;
        } else {
            final Long max = Collections.max(map.keySet());
            return max + 1L;
        }
    }
}
