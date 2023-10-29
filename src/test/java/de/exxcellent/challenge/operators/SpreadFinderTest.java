package de.exxcellent.challenge.operators;

import de.exxcellent.challenge.datastructure.TabularDataStructure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class SpreadFinderTest {


    @Test
    void getSpread1() {
        var resultCol = "result";
        var col1 = "col1";
        var col2 = "col2";
        var data = new ArrayList<String[]>();
        // headers
        data.add(new String[] { resultCol, col1, col2 });
        //data
        data.add(new String[] { "row1", "1", "10" });
        data.add(new String[] { "row2", "4", "5" });
        data.add(new String[] { "row3", "3", "8" });
        data.add(new String[] { "row4", "6", "9" });

        var dataStructure = new TabularDataStructure();
        dataStructure.addData(data);
        var spreadFinder = new SpreadFinder(dataStructure);
        var minDataElement = spreadFinder.getDataElementByMinSpread(
                dataStructure.createDataIdentifier(col1),
                dataStructure.createDataIdentifier(col2));
        String actual = minDataElement.getValue(dataStructure.createDataIdentifier(resultCol));

        Assertions.assertEquals("row2", actual);
    }

}
