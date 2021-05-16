package com.exercise.transitapi.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/operators")
public class OperatorController {

    @GetMapping
    public List<String> getRunningOperators(@RequestParam("startTime") String begin,
                                            @RequestParam("endTime") String end) {

        return List.of("It", "Worked!", begin, end);
    }

    @GetMapping("/{id}/vehicles")
    public List<String> getVehiclesIdsFromAnOperator(@PathVariable("id") String operatorId,
                                                     @RequestParam("startTime") String begin,
                                                     @RequestParam("endTime") String end) {

        return List.of("It", "Worked!", operatorId, begin, end);
    }

}
