package com.scanner.digital;

import com.scanner.digital.extractor.Extractor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FileScannerTest {

    @InjectMocks
    private FileScanner fileScanner;

    @Mock
    private Extractor extractor;

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
    public void shouldPrintDigitsToConsole_whenFileContainsSingleChunkOfDigits() {

        final String expectedChunk =
                " _  _  _  _  _  _  _  _  _\n" +
                "| || || || || || || || || |\n" +
                "|_||_||_||_||_||_||_||_||_|\n";

        final String filePath = "src/test/resources/singleChunkNineDigits.txt";

        when(extractor.extract(anyString(), anyInt(), anyInt())).thenReturn("000000000");

        assertThat(fileScanner.scanFileContents(filePath)).isTrue();
        assertThat(output.toString()).isEqualTo("\n000000000\n");
        verify(extractor).extract(expectedChunk, 9, 3);
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
