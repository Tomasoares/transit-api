package com.exercise.transitapi.manager.api;

import java.time.LocalDateTime;
import java.util.List;

public interface OperatorManager {

    List<String> getRunningOperators(LocalDateTime now, LocalDateTime now1);

}
