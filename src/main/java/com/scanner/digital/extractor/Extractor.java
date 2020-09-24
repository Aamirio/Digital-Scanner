package com.scanner.digital.extractor;

/**
 * An extractor used to extract digital characters from a given text
 */
public interface Extractor {

    /**
     * Extracts digital characters from a given text and returns it as plain text
     * @param text Given text to extract information from
     * @param noOfChars Number of digital characters to extract from text
     * @param charWidth Width (number of chars) of each digital character
     * @return extracted text
     */
    String extract(String text, int noOfChars, final int charWidth);
}
