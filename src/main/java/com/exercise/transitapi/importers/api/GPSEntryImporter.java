package com.exercise.transitapi.importers.api;

public interface GPSEntryImporter {

    boolean importData(String csvDirectory, String destination) throws Exception;
}
