package de.exxcellent.challenge.datastructure;

import de.exxcellent.challenge.abstractions.DataIdentifier;
import de.exxcellent.challenge.abstractions.DataElement;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class RowDataElement<K, V> implements DataElement<K, V> {
    private final Map<K, V> dataMap = new HashMap<>();

    public void addValue(final K key, final V value) {
        this.dataMap.put(key, value);
    }

    @Override
    public V geV(final DataIdentifier<K> identifier) {
        if (!dataMap.containsKey(identifier.geK()))
            return null;

        return dataMap.get(identifier.geK());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RowDataElement that)) return false;

        // ensure the content of the data maps are equal
        if (this.dataMap.size() != that.dataMap.size()) return false;
        AtomicBoolean allFieldsAreEqual = new AtomicBoolean(true);
        this.dataMap.forEach((k, v) -> {
            if (!that.dataMap.containsKey(k)) {
                allFieldsAreEqual.set(false);
            } else if (!Objects.equals(this.dataMap.get(k), that.dataMap.get(k))) {
                allFieldsAreEqual.set(false);
            }
        });
        return allFieldsAreEqual.get();
    }

}
