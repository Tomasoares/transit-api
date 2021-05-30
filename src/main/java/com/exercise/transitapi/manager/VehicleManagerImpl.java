package com.exercise.transitapi.manager;

import com.exercise.transitapi.entities.GPSEntry;
import com.exercise.transitapi.manager.api.VehicleManager;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleManagerImpl implements VehicleManager {

    @Override
    public List<GPSEntry> getVehicleTrace(LocalDateTime begin, LocalDateTime end, String vehicleId) {
        return null;
    }
}
