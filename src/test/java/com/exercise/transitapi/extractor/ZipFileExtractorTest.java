package com.exercise.transitapi.extractor;

import com.exercise.transitapi.extractors.ZipFileExtractor;
import com.exercise.transitapi.util.FileImporterConstants;
import com.exercise.transitapi.util.FileUnzipper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipFileExtractorTest {

    private ZipFileExtractor extractor = new ZipFileExtractor();

    private static final String TARGET_ZIP_FOLDER = "./src/test/resources/zip-extractor-test";
    private static final String DESTINATION = "./src/test/resources/zip-extractor-test-dest";
    private static final String EXPECTED_FILE_1 = DESTINATION + "/file1";
    private static final String EXPECTED_FILE_2 = DESTINATION + "/file2";

    @BeforeEach
    public void deleteFilesInDestinationFolder() throws IOException {
        FileUnzipper.cleanDirectory(DESTINATION);
    }

    @Test
    public void givenCsvZipDirectory_whenExtractFiles_shouldExtractAllFiles() throws Exception {
        extractor.extractFiles(TARGET_ZIP_FOLDER, DESTINATION);

        assertTrue(Files.exists(Path.of(EXPECTED_FILE_1)));
        assertTrue(Files.exists(Path.of(EXPECTED_FILE_2)));
    }
}
