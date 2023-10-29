package de.exxcellent.challenge.abstractions;

/**
 * A data element in a data structure.
 * Example 1: a data structure could be a table, then each row would be a data element.
 * Example 2: a data structure could be a tree of objects, then each object would be a data element.
 */
public interface DataElement {
    /**
     * Retrieve the textual representation of a cell in a data element.
     * @param identifier The unique identifier of a cell in the data element.
     *                   Example: the name of a column in a table.
     * @return The text in the cell.
     */
    String getValue(DataIdentifier identifier);
}
