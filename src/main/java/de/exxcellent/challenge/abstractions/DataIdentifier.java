package de.exxcellent.challenge.abstractions;

/**
 * A DataIdentifier uniquely identifies a cell in a data element of a data structure.
 */
public interface DataIdentifier<K> {
    /**
     * Retrieve the key that uniquely identifies a cell in a data element.
     * @return The key of the cell in a data element.
     */
    K geK();
}
