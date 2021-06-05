package com.exercise.transitapi.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUnzipperTest {

    private static String TEST_DIRECTORY = "./src/test/resources/zip-test";
    private static String TEST_DIRECTORY_TRANSIT_ZIP_FILE = "./src/test/resources/zip-test/test-file.zip";
    private static String TEST_DIRECTORY_TRANSIT_EXTRACTED_FILE = "./src/test/resources/zip-test/test-file.txt";

    @BeforeEach
    public void deleteOldGeneratedFile() throws IOException {
        FileUnzipper.deleteFile(TEST_DIRECTORY_TRANSIT_EXTRACTED_FILE);
    }

    @Test
    public void givenZipFile_whenUnzipFile_destinationMustNotBeEmpty() throws IOException {
        FileUnzipper.unzipFile(TEST_DIRECTORY_TRANSIT_ZIP_FILE, TEST_DIRECTORY);

        Path extractedFile = Path.of(TEST_DIRECTORY_TRANSIT_EXTRACTED_FILE);
        assertTrue(Files.exists(extractedFile));
    }

    @Test
    public void givenDirectory_whenFindZippedFile_mustRetrieveZippedFile() throws IOException {
        List<Path> zippedFile = FileUnzipper.findZippedFile(TEST_DIRECTORY);

        assertTrue(!zippedFile.isEmpty());
        assertTrue(zippedFile.get(0).toString().endsWith(".zip"));
    }

}
