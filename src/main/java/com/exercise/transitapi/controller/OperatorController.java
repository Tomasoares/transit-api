package com.exercise.transitapi.controller;

import com.exercise.transitapi.manager.api.OperatorManager;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/operators")
public class OperatorController {

    private OperatorManager manager;

    public OperatorController(OperatorManager manager) {
        super();
        this.manager = manager;
    }

    @GetMapping
    public ResponseEntity<?> getRunningOperators(@RequestParam("startTime") String begin,
                                                 @RequestParam("endTime") String end) {

        List<String> runningOperators = this.manager.getRunningOperators(LocalDateTime.now(), LocalDateTime.now());

        if (runningOperators.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(runningOperators);
    }

    @GetMapping("/{id}/vehicles")
    public ResponseEntity<?> getVehiclesIdsFromAnOperator(@PathVariable("id") String operatorId,
                                                     @RequestParam("startTime") String begin,
                                                     @RequestParam("endTime") String end,
                                                     @RequestParam("filter") String filter) {

        List<String> result = List.of("It", "Worked!", operatorId, begin, end, filter);
        return ResponseEntity.ok().body(result);
    }

}
