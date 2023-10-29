package de.exxcellent.challenge.datastructure;

import de.exxcellent.challenge.abstractions.DataIdentifier;

public record RowDataIdentifier<K>(K key) implements DataIdentifier<K> {

    @Override
    public K geK() {
        return key;
    }
}


