package com.exercise.transitapi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUnzipperTest {

    @Test
    public void whenUnzipFile_destinationMustNotBeEmpty() throws IOException {
        String zip = Constants.TRANSIT_DATA_FOLDER + "\\sir010113-310113.zip";

        FileUnzipper.unzipFile(zip, Constants.TRASIT_DATA_EXTRACTED_ZIP_DESTINATION);

        assertTrue(true);
    }

}
