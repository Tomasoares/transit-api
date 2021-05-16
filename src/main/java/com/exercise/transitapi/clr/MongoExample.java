package com.exercise.transitapi.clr;

import com.exercise.transitapi.entities.GPSEntry;
import com.exercise.transitapi.repositories.GPSEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Component
public class MongoExample implements CommandLineRunner {

    @Autowired
    private GPSEntryRepository repository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new GPSEntry(LocalDateTime.now(), "SL", 28.4958, 23.2435, "23", "123" ,true));

        List<GPSEntry> entries = repository.findByTimeBetween(LocalDateTime.now().minus(10, ChronoUnit.HOURS),
                LocalDateTime.now().plus(10, ChronoUnit.HOURS));

        System.out.println(entries);
    }
}
