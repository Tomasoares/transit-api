package com.exercise.transitapi.controller;

import com.exercise.transitapi.entities.GPSEntry;
import com.exercise.transitapi.manager.api.OperatorManager;
import com.exercise.transitapi.manager.api.VehicleManager;
import com.exercise.transitapi.util.ParameterConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private VehicleManager manager;

    public VehicleController(VehicleManager manager) {
        this.manager = manager;
    }

    @GetMapping("/{id}/gps-entries")
    public ResponseEntity<?> getVehicleTrace(@PathVariable("id") String vehicleId,
                                            @RequestParam("startTime") String paramBegin,
                                            @RequestParam("endTime") String paramEnd) {

        Optional<LocalDateTime> begin = ParameterConverter.toLocalDateTime(paramBegin);
        if (begin.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or Null begin date");
        }

        Optional<LocalDateTime> end = ParameterConverter.toLocalDateTime(paramEnd);
        if (end.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or Null end date");
        }

        List<GPSEntry> trace = this.manager.getVehicleTrace(begin.get(), end.get(), vehicleId);

        if (trace.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trace);
    }

}
