package com.exercise.transitapi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileUtilTest {

    @Test
    public void givenDirectory_whenFindZippedFile_mustRetrieveZippedFile() throws IOException {
        List<Path> zippedFile = FileUtil.findFiles(FileConstants.TEST_DIRECTORY, Extension.ZIP);

        assertFalse(zippedFile.isEmpty());
        assertTrue(zippedFile.get(0).toString().endsWith(".zip"));
    }

    @Test
    public void whenGetFilesFromPath_pathsShouldNotBeEmpty() throws IOException {
        String path = "";
        List<Path> paths = FileUtil.findFiles(path, Extension.CSV);

        assertFalse(paths.isEmpty(), "paths shouldn't be null");
    }
}
