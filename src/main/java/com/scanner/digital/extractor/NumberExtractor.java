package com.scanner.digital.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Extracts digital numbers
 */
public class NumberExtractor implements Extractor {

    @Override
    public String extract(final String text, final int noOfChars, final int charWidth) {

        final StringBuilder extractedText = new StringBuilder();
        final List<String> digitalChars = new ArrayList<>();

        int cursor = 0;
        int digitalCharsIndex = 0;

        while (cursor != text.length()) {
            String charSegment = text.substring(cursor, cursor + charWidth);

            if (digitalCharsIndex >= digitalChars.size()) {
                digitalChars.add(charSegment);
            } else {
                charSegment = String.join("\n", digitalChars.get(digitalCharsIndex), charSegment);
                digitalChars.set(digitalCharsIndex, charSegment);
            }

            cursor += charWidth;

            if (++digitalCharsIndex == noOfChars) { digitalCharsIndex = 0; }

            //omit line break from given text as already joining with line break delimiter above
            if (text.charAt(cursor) == '\n') { cursor++;}
        }

        digitalChars.forEach(dc -> {
            String character = Arrays.stream(DigitalNumber.values())
                    .filter(n -> n.value.equals(dc))
                    .findAny().orElse(null).name;

            extractedText.append(character);
        });

        return extractedText.toString();
    }
}
