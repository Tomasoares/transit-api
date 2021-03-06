package com.exercise.transitapi.clr;

import com.exercise.transitapi.importers.api.GPSEntryImporter;
import com.exercise.transitapi.util.FileImporterConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GPSDataImporterCLR implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(GPSDataImporterCLR.class);

    @Autowired
    private GPSEntryImporter importer;

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Starting MongoDBImporter");

        LOGGER.info("Downloading Compressed Data");

        LOGGER.info("Importing Data To Mongo Database");

        boolean success = importer.importData(FileImporterConstants.TRANSIT_DATA_FOLDER,
                                              FileImporterConstants.TRANSIT_DATA_EXTRACTED_ZIP_DESTINATION);

        if (success) {
            LOGGER.info("Imported with success!");
        } else {
            LOGGER.warn("Error importing data into MongoDB");
        }
    }
}
