package com.exercise.transitapi.util;

import org.apache.logging.log4j.util.Strings;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class FileUtil {

    private FileUtil() {}

    public static void deleteFile(String file) throws IOException {
        Files.deleteIfExists(Path.of(file));
    }

    public static void cleanDirectory(String file) throws IOException {
        FileUtils.cleanDirectory(new File(file));
    }

    public static List<Path> findFiles(String directory, Extension extension) throws IOException {
        Path path = Path.of(directory);

        if (!Files.exists(path)) {
            throw new IOException("Directory doesn't exist! Directory: " + directory);
        }

        if (!Files.isDirectory(path)) {
            throw new IOException("Directory is invalid! Directory: " + directory);
        }

        String concat = String.join("", ".", extension.getValue());

        try (Stream<Path> files = Files.walk(path)) {
            return files.filter(p -> p.toString().endsWith(concat))
                    .collect(Collectors.toList());
        }
    }
}
