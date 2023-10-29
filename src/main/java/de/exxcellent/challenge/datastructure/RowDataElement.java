package de.exxcellent.challenge.datastructure;

import de.exxcellent.challenge.abstractions.DataIdentifier;
import de.exxcellent.challenge.abstractions.DataElement;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class RowDataElement implements DataElement {
    private final Map<String, String> dataMap = new HashMap<>();

    public void addValue(final String key, final String value) {
        this.dataMap.put(key, value);
    }

    @Override
    public String getValue(final DataIdentifier identifier) {
        if (!dataMap.containsKey(identifier.getKey()))
            return null;

        return dataMap.get(identifier.getKey());
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
