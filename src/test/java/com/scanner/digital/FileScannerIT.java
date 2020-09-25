package com.scanner.digital;

import com.scanner.digital.extractor.NumberExtractor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FileScannerIT {

    FileScanner fileScanner = new FileScanner(new NumberExtractor());

    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorOutput = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(output));
        System.setErr(new PrintStream(errorOutput));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void shouldPrintDigitsToConsole_whenFileContainsSingleChunkOfDigits() {

        final String filePath = "src/test/resources/singleChunkNineDigits.txt";

        assertThat(fileScanner.scanFileContents(filePath)).isTrue();
        assertThat(output.toString()).isEqualTo("\n000000000\n");
    }

    @Test
    public void shouldPrintDigitsToConsole_whenFileContainsMultipleChunksOfDigits() {

        final String filePath = "src/test/resources/multipleChunksNineDigits.txt";

        assertThat(fileScanner.scanFileContents(filePath)).isTrue();
        assertThat(output.toString()).isEqualTo("\n000000000\n123456789\n");
    }

    @Test
    public void shouldPrintQuestionMarkToConsole_whenFileContainsUnrecognisedDigit() {

        final String filePath = "src/test/resources/singleChunkUnrecognisedDigit.txt";

        assertThat(fileScanner.scanFileContents(filePath)).isTrue();
        assertThat(output.toString()).isEqualTo("\n00000000?\n");
    }
}
