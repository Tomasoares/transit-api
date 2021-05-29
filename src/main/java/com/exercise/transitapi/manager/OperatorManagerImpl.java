package com.exercise.transitapi.manager;

import com.exercise.transitapi.manager.api.OperatorManager;
import com.exercise.transitapi.util.Filters;

import java.time.LocalDateTime;
import java.util.List;

public class OperatorManagerImpl implements OperatorManager {

    @Override
    public List<String> getRunningOperators(LocalDateTime now, LocalDateTime now1) {
        return null;
    }

    @Override
    public List<String> getVehiclesIds(String operatorId, LocalDateTime localDateTime, LocalDateTime localDateTime1, List<Filters> filters) {
        return null;
    }

}
