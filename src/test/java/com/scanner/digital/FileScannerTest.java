package com.scanner.digital;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FileScannerTest {

    FileScanner fileScanner = new FileScanner();

    @Test
    public void shouldReturnTrue_whenFileReadsSuccessfully(){

        final String filePath = "src/test/resources/blankFile.txt";

        assertThat(fileScanner.scanFileContents(filePath)).isTrue();
    }
}
