package com.exercise.transitapi.importers;

import com.exercise.transitapi.extractors.ZipFileExtractor;
import com.exercise.transitapi.importers.api.GPSEntryImporter;
import com.exercise.transitapi.util.Extension;
import com.exercise.transitapi.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.List;

@Component
public class GPSEntryImporterImpl implements GPSEntryImporter {

    private static final Logger LOGGER = LoggerFactory.getLogger(GPSEntryImporterImpl.class);

    @Autowired
    private ZipFileExtractor extractor;

    @Override
    public boolean importData(String csvDirectory, String destination) throws Exception {
        LOGGER.debug("Extracting all files");
        this.extractor.extractFiles(csvDirectory, destination);

        LOGGER.debug("Getting files references");
        List<Path> files = FileUtil.findFiles(csvDirectory, Extension.CSV);

        LOGGER.debug("Importing files: {}", files);


        return false;
    }
}
