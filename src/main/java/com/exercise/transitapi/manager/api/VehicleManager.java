package com.exercise.transitapi.manager.api;

import com.exercise.transitapi.entities.GPSEntry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface VehicleManager {
    List<GPSEntry> getVehicleTrace(LocalDateTime begin, LocalDateTime end, String vehicleId);
}
