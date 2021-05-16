package com.exercise.transitapi.repositories;

import com.exercise.transitapi.entities.GPSEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GPSEntryRepository extends MongoRepository<GPSEntry, String> {

    public List<GPSEntry> findByTimeBetween(@Param("begin") LocalDateTime begin,
                                      @Param("end") LocalDateTime end);

}
