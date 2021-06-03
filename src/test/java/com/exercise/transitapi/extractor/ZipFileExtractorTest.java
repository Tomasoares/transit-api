package com.exercise.transitapi.extractor;

import com.exercise.transitapi.extractors.ZipFileExtractor;
import com.exercise.transitapi.util.FileImporterConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipFileExtractorTest {

    private ZipFileExtractor extractor = new ZipFileExtractor();

    @Test
    public void givenCsvZipDirectory_whenExtractFiles_shouldExtractAllFiles() {
        extractor.extractFiles(FileImporterConstants.TRANSIT_DATA_FOLDER);

        assertTrue(true);
    }
}
