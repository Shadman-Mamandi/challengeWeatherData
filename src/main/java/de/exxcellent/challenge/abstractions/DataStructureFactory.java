package de.exxcellent.challenge.abstractions;

import java.util.List;

/**
 * Produces data structures based on the type of available data.
 */
public interface DataStructureFactory<K, V> {
    /**
     * Produces a data structure for tabular data with columns and rows.
     * @param tabularData The data in rows, separated into columns.
     * @return A data structure filled with the given tabular data.
     */
    DataStructure<K, V> produce(K[] keys, List<V[]> tabularData);
}
