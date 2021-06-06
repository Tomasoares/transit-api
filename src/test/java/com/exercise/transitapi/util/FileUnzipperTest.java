package com.exercise.transitapi.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUnzipperTest {

    @BeforeEach
    public void deleteOldGeneratedFile() throws IOException {
        FileUtil.deleteFile(FileConstants.TEST_DIRECTORY_TRANSIT_EXTRACTED_FILE);
    }

    @Test
    public void givenZipFile_whenUnzipFile_destinationMustNotBeEmpty() throws IOException {
        FileUnzipper.unzipFile(FileConstants.TEST_DIRECTORY_TRANSIT_ZIP_FILE, FileConstants.TEST_DIRECTORY);

        Path extractedFile = Path.of(FileConstants.TEST_DIRECTORY_TRANSIT_EXTRACTED_FILE);
        assertTrue(Files.exists(extractedFile));
    }

}
