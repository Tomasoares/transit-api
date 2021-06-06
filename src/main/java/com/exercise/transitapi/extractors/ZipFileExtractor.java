package com.exercise.transitapi.extractors;

import com.exercise.transitapi.util.Extension;
import com.exercise.transitapi.util.FileImporterConstants;
import com.exercise.transitapi.util.FileUnzipper;
import com.exercise.transitapi.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Component
public class ZipFileExtractor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZipFileExtractor.class);

    public void extractFiles(String path, String destinationPath) throws Exception {
        try {
            LOGGER.debug("Finding downloaded zipped file at path: {}", path);
            this.extractMainZipFile(path, destinationPath);

            LOGGER.debug("Finding subzipped files at: {}", destinationPath);
            this.extractSubZippedFiles(destinationPath);


        } catch (IOException e) {
            LOGGER.error("Error trying to extract files", e);
            throw e;
        }
    }

    private void extractMainZipFile(String path, String destinationPath) throws Exception {
        List<Path> zippedFiles = FileUtil.findFiles(path, Extension.ZIP);

        if (zippedFiles.isEmpty()) {
            throw new Exception("No zipped File has been found! Path: " + path);
        }

        Path zip = zippedFiles.get(0);
        LOGGER.debug("Unzipping file: {}", zip.toString());
        FileUnzipper.unzipFile(zip, destinationPath);
    }

    private void extractSubZippedFiles(String destinationPath) throws IOException {
        List<Path> zippedSubFiles = FileUtil.findFiles(destinationPath, Extension.GZ);

        if (zippedSubFiles.isEmpty()) {
            LOGGER.warn("No zipped subfolders encountered!");
            return;
        }

        for (Path zip : zippedSubFiles) {
            LOGGER.debug("Unzipping file: {}", zip.toString());
            FileUnzipper.unzipFile(zip, destinationPath);
        }
    }
}
