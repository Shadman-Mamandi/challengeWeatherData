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
public class SpreadFinder {
    private final DataStructure dataStructure;
    // this cache keeps the calculated spreads for each row to prevent duplicate calculations
    private final Map<DataElement, Integer> spreadCache = new HashMap<>();

    /**
     * Initializes a new instance of the SpreadFinder class.
     * @param dataStructure The data structure where the SpreadFinder
     *                      will find data elements for calculating the spread.
     */
    public SpreadFinder(final DataStructure dataStructure) {
        this.dataStructure = dataStructure;
    }

    /**
     * Find the data element with the lowest spread among all data elements in the data structure.
     * @param column1Identity The identifier of a cell in a data element of the data structure.
     *                        This cell-1 is used to calculate the spread to cell-2.
     * @param column2Identity The identifier of a cell in a data element of the data structure.
     *                        This cell-2 is used to calculate the spread to cell-1.
     * @return
     */
    public DataElement getDataElementByMinSpread(
            final DataIdentifier column1Identity,
            final DataIdentifier column2Identity) {
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
    private int calculateSpread(DataElement row,
                                DataIdentifier col1id,
                                DataIdentifier col2id) {
        if (spreadCache.containsKey(row)) {
            return spreadCache.get(row);
        }
        String strValue1 = row.getValue(col1id);
        String strValue2 = row.getValue(col2id);
        int intValue1 = Integer.parseInt(strValue1);
        int intValue2 = Integer.parseInt(strValue2);
        int spread = Math.abs(intValue1 - intValue2);
        spreadCache.put(row, spread);
        return spread;
    }
}
