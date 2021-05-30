package com.exercise.transitapi.util;

import org.apache.logging.log4j.util.Strings;
import org.springframework.format.datetime.standard.InstantFormatter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class ParameterConverter {

    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("uuuuMMddHHmm");
    public static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    public static final int TIME_LENGTH = 4;

    private ParameterConverter() {}

    public static Optional<LocalDateTime> toLocalDateTime(String date) {
        if (Strings.isBlank(date)) {
            return Optional.empty();
        }

        LocalDateTime finalDate;

        try {
            if (date.length() == TIME_LENGTH) {
                finalDate = LocalDateTime.of(LocalDate.now(), LocalTime.parse(date, TIME_FORMAT));
            } else {
                finalDate = LocalDateTime.parse(date, DATETIME_FORMAT);
            }

            return Optional.of(finalDate);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static List<Filters> toFilters(String filter) {
        if (filter.isBlank()) {
            return List.of();
        }

        return Arrays.stream(Filters.values())
                .filter(v -> filter.contains(v.getParamString()))
                .collect(Collectors.toList());
    }


}
