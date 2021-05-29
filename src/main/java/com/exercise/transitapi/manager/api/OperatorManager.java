package com.exercise.transitapi.manager.api;

import com.exercise.transitapi.util.Filters;

import java.time.LocalDateTime;
import java.util.List;

public interface OperatorManager {

    List<String> getRunningOperators(LocalDateTime now, LocalDateTime now1);

    List<String> getVehiclesIds(String operatorId, LocalDateTime localDateTime, LocalDateTime localDateTime1, List<Filters> filters);

}
