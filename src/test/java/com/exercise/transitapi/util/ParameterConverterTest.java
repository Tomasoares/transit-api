package com.exercise.transitapi.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ParameterConverterTest {

    @Test
    public void givenValidDatetime_whenToLocalDateTime_shouldReturnValidDateTime() {
        String date = "202105292127";

        Optional<LocalDateTime> result = ParameterConverter.toLocalDateTime(date);

        assertFalse(result.isEmpty());
        assertEquals(result.get(), LocalDateTime.of(2021, 5, 29, 21, 27));
    }

    @Test
    public void givenInvalidDatetime_whenToLocalDateTime_shouldReturnEmpty() {
        String date = "20210529212";

        Optional<LocalDateTime> result = ParameterConverter.toLocalDateTime(date);

        assertTrue(result.isEmpty());
    }

    @Test
    public void givenNoDatetime_whenToLocalDateTime_shouldReturnEmpty() {
        String date = "";

        Optional<LocalDateTime> result = ParameterConverter.toLocalDateTime(date);

        assertTrue(result.isEmpty());
    }

    @Test
    public void givenNoFilterString_whenToFilters_shouldReturnEmptyList() {
        String filter = "";

        List<Filters> filters = ParameterConverter.toFilters(filter);

        assertTrue(filters.isEmpty());
    }

    @Test
    public void givenIncorrectFilterString_whenToFilters_shouldReturnEmptyList() {
        String filter = "incorrectValue";

        List<Filters> filters = ParameterConverter.toFilters(filter);

        assertTrue(filters.isEmpty());
    }

    @Test
    public void givenAtStopFilterString_whenToFilters_shouldReturnListWithAtStop() {
        String filter = Filters.AT_STOP.getParamString();

        List<Filters> filters = ParameterConverter.toFilters(filter);

        assertEquals(List.of(Filters.AT_STOP), filters);
    }
}
