package com.scanner.digital;

import com.scanner.digital.extractor.Extractor;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.stream.IntStream;

/**
 * Extracts file contents using provided Extractor and prints to console.
 *
 */
@Component
public class FileScanner {

    private Extractor extractor;

    public FileScanner(Extractor extractor) {
        this.extractor = extractor;
    }

    /**
     * Scans file contents and prints desired characters to console.
     * @param filePath path name for file e.g. /Users/Bob/Docs/myFile.txt
     * @return False if unable to read file or file path is invalid. True if successfully printed to console.
     *
     */
    public boolean scanFileContents(String filePath) {

        final int noOfChars = 9;
        final int charWidth = 3;
        final int charHeight = 3;
        final int lineLength = noOfChars * charWidth;
        final int chunkLength = lineLength * charHeight;

        try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath).getAbsolutePath()))) {

            StringBuilder extractedText = new StringBuilder();
            StringBuilder chunk = new StringBuilder();
            String line;

            while( (line = br.readLine()) != null ) {

                if (!line.equals("")) {
                    if (line.length() < lineLength) { line = padLineWithWhiteSpace(line, lineLength); }
                    chunk.append(line);
                    chunk.append("\n");
                }

                if (chunk.length() == chunkLength + charHeight) {
                    extractedText.append("\n");
                    extractedText.append(extractor.extract(chunk.toString(), noOfChars, charWidth));
                    chunk = new StringBuilder();
                }
            }

            System.out.println(extractedText.toString());

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

    /*
     * This is required because br.readLine trims any white space preceding a line break
     */
    private String padLineWithWhiteSpace(String line, int lineLength) {

        final StringBuilder lineBuilder = new StringBuilder(line);

        IntStream.range(0, lineLength - line.length())
                .forEach(i -> lineBuilder.append(' '));

        return lineBuilder.toString();
    }
}
