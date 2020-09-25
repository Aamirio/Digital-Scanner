package com.scanner.digital.extractor;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberExtractorTest {

   NumberExtractor extractor = new NumberExtractor();

    @Test
    public void shouldReturnSingleDigit_whenGivenTextContainsSingleDigitalDigit() {

        final String inputText =
                "   \n" +
                "  |\n" +
                "  |\n";

        final String extractedText = extractor.extract(inputText, 1, 3);

        assertThat(extractedText).isNotEmpty();
        assertThat(extractedText).isEqualTo("1");
    }

    @Test
    public void shouldReturnNineDigits_whenGivenTextContainsNineDigitalDigits() {

        final String inputText =
                "    _  _     _  _  _  _  _ \n" +
                "  | _| _||_||_ |_   ||_||_|\n" +
                "  ||_  _|  | _||_|  ||_| _|\n";

        final String extractedText = extractor.extract(inputText, 9, 3);

        assertThat(extractedText).isNotEmpty();
        assertThat(extractedText).isEqualTo("123456789");
    }

    @Test
    public void shouldReturnQuestionMark_whenGivenTextContainsUnrecognisedDigit() {

        final String inputText =
                        "    _  _     _  _  _     _ \n" +
                        "  | _| _||_||_ |_   ||_||_|\n" +
                        "  ||_  _|  | _||_|  ||_| _|\n";

        final String extractedText = extractor.extract(inputText, 9, 3);

        assertThat(extractedText).isNotEmpty();
        assertThat(extractedText).isEqualTo("1234567?9");
    }
}