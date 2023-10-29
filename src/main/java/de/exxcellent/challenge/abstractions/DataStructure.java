package de.exxcellent.challenge.abstractions;

import org.apache.commons.lang3.tuple.Pair;

import java.util.function.Function;

/**
 * A data structure for holding arbitrary data elements and executing algorithms on the data.
 */
public interface DataStructure {
    /**
     * The find function iterates over all data elements,
     * comparing the result of the previous comparison with the currently visited data element.
     * The algorithm therefore works like bubble sort with (O(n)).
     * @param compareFunction A function for comparing two data elements.
     * @return The data element that is the result of comparing all data elements in the data structure with each other.
     */
    DataElement find(Function<Pair<DataElement, DataElement>, DataElement> compareFunction);

    /**
     * Creates an identifier for a data cell in a data element.
     * Example: This could be the name of a column in a table, to identify a cell in a row.
     * @param dataFieldName The name of the data field
     *                      that identifies this field in a data element of the data structure.
     * @return The identifier, or NULL if the data field is not found in the data structure.
     */
    DataIdentifier createDataIdentifier(String dataFieldName);
}
