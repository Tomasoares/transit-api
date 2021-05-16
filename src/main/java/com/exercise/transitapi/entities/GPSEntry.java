package com.exercise.transitapi.entities;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@ToString
public class GPSEntry {

    @Id
    public String id;

    @NonNull
    public LocalDateTime time;

    @NonNull
    public String operator;

    @NonNull
    public Double lat;

    @NonNull
    public Double lon;

    @NonNull
    public String vehicleId;

    @NonNull
    public String stopId;

    @NonNull
    public boolean atStop;

}
