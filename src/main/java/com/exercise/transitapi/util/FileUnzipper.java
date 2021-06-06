package com.exercise.transitapi.util;

import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class FileUnzipper {

    private FileUnzipper() {}

    public static void unzipFile(String fileToUnzip, String destinationPath) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(fileToUnzip))) {
            unzipFile(destinationPath, zis);
        }
    }

    public static void unzipFile(Path fileToUnzip, String destinationPath) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(fileToUnzip.toFile()))) {
            unzipFile(destinationPath, zis);
        }
    }

    private static void unzipFile(String destinationPath, ZipInputStream zis) throws IOException {
        ZipEntry zippedFile = zis.getNextEntry();

        while(zippedFile != null) {
            Path unzippedFile = Path.of(destinationPath + "\\" + zippedFile.getName());
            unzipFile(zis, unzippedFile);
            zippedFile = zis.getNextEntry();
        }

        zis.closeEntry();
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
}
