package com.exercise.transitapi.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class FileUnzipper {

    private FileUnzipper() {}

    public static void unzipFile(String fileToUnzip, String destinationPath) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(fileToUnzip))) {
            ZipEntry zippedFile = zis.getNextEntry();

            while(zippedFile != null) {
                Path unzippedFile = Path.of(destinationPath + "\\" + zippedFile.getName());

                unzipFile(zis, unzippedFile);

                zippedFile = zis.getNextEntry();
            }

            zis.closeEntry();
        }
    }

    private static void unzipFile(ZipInputStream zis, Path unzippedFile) throws IOException {
        byte[] dataBuffer = new byte[1024];

        try (FileOutputStream dest = new FileOutputStream(unzippedFile.toFile())) {
            int readBytes;

            while((readBytes = zis.read(dataBuffer)) > 0) {
                dest.write(dataBuffer, 0, readBytes);
            }
        }
    }

    public static void deleteFile(String file) throws IOException {
        Files.deleteIfExists(Path.of(file));
    }

    public static Optional<Path> findZippedFile(String directory) throws IOException {
        Path path = Path.of(directory);

        if (!Files.exists(path)) {
            throw new IOException("Directory doesn't exist! Directory: " + directory);
        }

        if (!Files.isDirectory(path)) {
            throw new IOException("Directory is invalid! Directory: " + directory);
        }

        try (Stream<Path> files = Files.walk(path)) {
            return files.filter(p -> p.toString().endsWith(".zip"))
                    .findFirst();
        }
    }
}
