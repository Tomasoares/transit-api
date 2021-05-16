package com.exercise.transitapi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

public class FileUtilTest {

    @Test
    public void whenGetFilesFromPath_pathsShouldntBeEmpty() {
        String path = "";
        List<Path> paths = FileUtil.getFilesFromPath(path);

        Assertions.assertTrue(!paths.isEmpty(), "paths shouldn't be null");
    }
}
