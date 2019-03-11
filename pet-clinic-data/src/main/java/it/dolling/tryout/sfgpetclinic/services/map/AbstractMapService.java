package it.dolling.tryout.sfgpetclinic.services.map;

import com.sun.istack.internal.NotNull;
import it.dolling.tryout.sfgpetclinic.model.BaseEntity;
import it.dolling.tryout.sfgpetclinic.services.CrudService;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> implements CrudService<T, ID> {

    protected Map<Long, T> map = new HashMap<>();

    @Override
    public Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    @Override
    public T findById(ID id) {
        return map.get(id);
    }

    @Override
    public T save(@NotNull T object) {
        if (object.getId() == null) {
            object.setId(getNextId());
        }
        map.put(object.getId(), object);
        return object;
    }

    void deleteById(ID id) {
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
