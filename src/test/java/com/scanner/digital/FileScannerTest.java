package com.scanner.digital;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class FileScannerTest {

    private final FileScanner fileScanner = new FileScanner();
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
    public void shouldReturnTrue_whenFileReadsSuccessfully() {

        final String filePath = "src/test/resources/blankFile.txt";

        assertThat(fileScanner.scanFileContents(filePath)).isTrue();
    }

    @Test
    public void shouldReturnFalseAndPrintErrorToConsole_whenInvalidFilePathProvided() {

        final String filePath = "src/test/resources/iDontExist.txt";
        final String expectedOutput = "File not found\n";

        assertThat(fileScanner.scanFileContents(filePath)).isFalse();
        assertThat(output.toString()).isBlank();
        assertThat(errorOutput.toString()).isEqualTo(expectedOutput);
    }
}
