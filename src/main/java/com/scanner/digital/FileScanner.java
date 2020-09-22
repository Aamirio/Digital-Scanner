package com.scanner.digital;

import java.io.*;

/**
 * Extracts file contents using provided Extractor and prints to console.
 *
 */
public class FileScanner {

    /**
     * Scans file contents and prints desired characters to console.
     * @param filePath path name for file e.g. /Users/Bob/Docs/myFile.txt
     * @return False if unable to read file or file path is invalid. True if successfully printed to console.
     *
     */
    public boolean scanFileContents(String filePath) {

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath).getAbsolutePath()))) {
            return true;
        }

        catch (FileNotFoundException e) {
            System.err.println("File not found");
            return false;
        }
        catch (IOException e) {
            System.err.println("Unable to read the file.");
            return false;
        }
    }
}
