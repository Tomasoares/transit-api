package com.exercise.transitapi.extractors;

import com.exercise.transitapi.util.FileUnzipper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

@Component
public class ZipFileExtractor {

    public void extractFiles(String path) {
        try {
            Optional<Path> zippedFile = FileUnzipper.findZippedFile(path);

            //FileUnzipper.unzipFile(zippedFile);
            //TODO code
        } catch (IOException e) {
            //TODO Logger
            e.printStackTrace();
        }
    }
}
