package de.exxcellent.challenge.abstractions;


import java.io.IOException;
import java.nio.file.Path;

/**
 * A FileReader converts the data in an input file into a data structure.
 */
public interface FileReader<K, V> {

    /**
     * Reads a file and puts all content of the file into a data structure.
     * @param filePath The absolute path to the file.
     * @return The filled data structure.
     * @throws IOException When the file fails to load.
     */
    DataStructure<K, V> readFileToDataStructure(Path filePath)  throws IOException;
}
