package com.exercise.transitapi.controller;

import com.exercise.transitapi.manager.api.OperatorManager;
import com.exercise.transitapi.util.Filters;
import com.exercise.transitapi.util.ParameterConverter;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/operators")
public class OperatorController {

    private OperatorManager manager;

    public OperatorController(OperatorManager manager) {
        super();
        this.manager = manager;
    }

    @GetMapping
    public ResponseEntity<?> getRunningOperators(@RequestParam("startTime") String paramBegin,
                                                 @RequestParam("endTime") String paramEnd) {

        Optional<LocalDateTime> begin = ParameterConverter.toLocalDateTime(paramBegin);

        if (begin.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or Null begin date");
        }

        Optional<LocalDateTime> end = ParameterConverter.toLocalDateTime(paramEnd);

        if (end.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or Null end date");
        }

        List<String> runningOperators = this.manager.getRunningOperators(begin.get(), end.get());

        if (runningOperators.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(runningOperators);
    }

    @GetMapping("/{id}/vehicles")
    public ResponseEntity<?> getVehiclesIdsFromAnOperator(@PathVariable("id") String operatorId,
                                                     @RequestParam("startTime") String paramBegin,
                                                     @RequestParam("endTime") String paramEnd,
                                                     @RequestParam("filter") String filter) {

        Optional<LocalDateTime> begin = ParameterConverter.toLocalDateTime(paramBegin);
        if (begin.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or Null begin date");
        }

        Optional<LocalDateTime> end = ParameterConverter.toLocalDateTime(paramEnd);
        if (end.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid or Null end date");
        }

        List<Filters> filters = ParameterConverter.toFilters(filter);
        if (Strings.isNotBlank(filter) && filters.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid filter");
        }

        List<String> vehiclesIds = this.manager.getVehiclesIds(operatorId, begin.get(), end.get(), filters);

        if (vehiclesIds.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(vehiclesIds);
    }

}
