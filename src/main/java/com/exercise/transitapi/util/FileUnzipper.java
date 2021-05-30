package com.exercise.transitapi.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class FileUnzipper {

    private FileUnzipper() {}

    public static void unzipFile(String file, String destinationPath) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(file))) {
            ZipEntry zippedFile = zis.getNextEntry();

            while(zippedFile != null) {
                Path unzippedFile = Path.of(destinationPath + "\\" + zippedFile.getName());

                unzipFIle(zis, unzippedFile);

                zippedFile = zis.getNextEntry();
            }

            zis.closeEntry();
        }
    }

    private static void unzipFIle(ZipInputStream zis, Path unzippedFile) throws IOException {
        byte[] dataBuffer = new byte[1024];

        try (FileOutputStream dest = new FileOutputStream(unzippedFile.toFile())) {
            int readBytes;

            while((readBytes = zis.read(dataBuffer)) > 0) {
                dest.write(dataBuffer, 0, readBytes);
            }
        }
    }
}
