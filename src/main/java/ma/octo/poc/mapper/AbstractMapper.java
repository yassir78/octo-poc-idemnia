package ma.octo.poc.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractMapper<K, V> {

    public abstract K toDto(V entity);

    public abstract V toEntity(K dto);

    public List<K> toDtos(List<V> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<K> items = new ArrayList<>();
            for (V entity : entities) {
                items.add(toDto(entity));
            }
            return items;
        }
    }

    public List<V> toEntities(List<K> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<V> vos = new ArrayList<>();
            for (K dto : dtos) {
                vos.add(toEntity(dto));
            }
            return vos;
        }
    }

}
