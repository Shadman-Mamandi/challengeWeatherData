package de.exxcellent.challenge.datastructure;

import de.exxcellent.challenge.abstractions.DataIdentifier;

public record RowDataIdentifier(String key) implements DataIdentifier {
    @Override
    public String getKey() {
        return key;
    }
}
