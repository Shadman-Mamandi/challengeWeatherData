package de.exxcellent.challenge.abstractions;

import java.nio.file.Path;

/**
 * A FileReader converts the data in an input file into a data structure.
 */
public interface FileReader {

    /**
     * Reads a file and puts all content of the file into a data structure.
     * @param filePath The absolute path to the file.
     * @return The filled data structure.
     * @throws Exception
     */
    DataStructure readFileToDataStructure(Path filePath) throws Exception;
}
