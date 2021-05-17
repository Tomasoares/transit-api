package com.exercise.transitapi.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @GetMapping("/{id}/gps-entries")
    public List<String> getRunningOperators(@PathVariable("id") String vehicleId,
                                            @RequestParam("startTime") String begin,
                                            @RequestParam("endTime") String end) {

        return List.of("It", "Worked!", vehicleId, begin, end);
    }

}
