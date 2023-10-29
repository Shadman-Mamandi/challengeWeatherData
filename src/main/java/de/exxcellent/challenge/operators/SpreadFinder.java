package de.exxcellent.challenge.operators;

import de.exxcellent.challenge.abstractions.DataIdentifier;
import de.exxcellent.challenge.abstractions.DataStructure;
import de.exxcellent.challenge.abstractions.DataElement;
import java.util.HashMap;
import java.util.Map;

/**
 * The SpreadFinder calculates the spread between two given data cells
 * in all data elements of a data structure.
 */
public class SpreadFinder<K, V> {
    private final DataStructure<K, V> dataStructure;
    // this cache keeps the calculated spreads for each row to prevent duplicate calculations
    private final Map<DataElement<K, V>, Integer> spreadCache = new HashMap<>();

    /**
     * Initializes a new instance of the SpreadFinder class.
     * @param dataStructure The data structure where the SpreadFinder
     *                      will find data elements for calculating the spread.
     */
    public SpreadFinder(final DataStructure<K, V> dataStructure) {
        this.dataStructure = dataStructure;
    }

    /**
     * Find the data element with the lowest spread among all data elements in the data structure.
     * @param column1Identity The identifier of a cell in a data element of the data structure.
     *                        This cell-1 is used to calculate the spread to cell-2.
     * @param column2Identity The identifier of a cell in a data element of the data structure.
     *                        This cell-2 is used to calculate the spread to cell-1.
     * @return The data element with the lowest spread.
     */
    public DataElement<K, V> getDataElementByMinSpread(
            final DataIdentifier<K> column1Identity,
            final DataIdentifier<K> column2Identity) {
        // check if the identifiers are valid
        if (column1Identity == null || column2Identity == null)
            throw new IllegalArgumentException("Column names are not found in the data set");

        // visit the data elements in the data structure to find the lowest spread.
        return dataStructure.find(pair -> {
            var left = pair.getLeft();
            var right = pair.getRight();
            if (left == null) return right;
            if (right == null) return left;

            int leftSpread = calculateSpread(left, column1Identity, column2Identity);
            int rightSpread = calculateSpread(right, column1Identity, column2Identity);

            // if the spread in the left data element is bigger,
            // then the right data element is closer to the minimum.
            if (leftSpread > rightSpread) return right;
            else return left;
        });
    }

    /**
     * Calculates the spread between two columns.
     * If the spread of a column has already been calculated, it will use the value from the cache.
     * @param row The data element containing two columns with integer values for a spread calculation.
     * @param col1id The id of one column for the spread calculation.
     * @param col2id The id of one column for the spread calculation.
     * @return The spread between column 1 and column 2.
     */
    private int calculateSpread(DataElement<K, V> row,
                                DataIdentifier<K> col1id,
                                DataIdentifier<K> col2id) {
        if (spreadCache.containsKey(row)) {
            return spreadCache.get(row);
        }
        V strValue1 = row.geValue(col1id);
        V strValue2 = row.geValue(col2id);
        int inV1 = Integer.parseInt(strValue1.toString());
        int inV2 = Integer.parseInt(strValue2.toString());
        int spread = Math.abs(inV1 - inV2);
        spreadCache.put(row, spread);
        return spread;
    }
}
