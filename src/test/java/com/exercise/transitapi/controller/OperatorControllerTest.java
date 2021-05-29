package com.exercise.transitapi.controller;

import com.exercise.transitapi.manager.api.OperatorManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OperatorControllerTest {

    public OperatorController operatorController;

    @Mock
    public OperatorManager manager;

    public OperatorControllerTest() {
        super();
    }

    @BeforeEach
    public void init() {
        this.operatorController = new OperatorController(this.manager);
    }

    @Test
    public void givenValidParameters_whenGetRunningOperator_shouldReturnGPSEntry() {
        List<String> expected = List.of("1", "2");
        when(manager.getRunningOperators(any(), any())).thenReturn(expected);

        String begin = "1000";
        String end = "1300";
        ResponseEntity response = this.operatorController.getRunningOperators(begin, end);

        assertLinesMatch(expected, (List<String>) response.getBody());
    }

    @Test
    public void givenValidParameters_whenGetRunningOperator_shouldReturnStatus200() {
        List<String> expected = List.of("1", "2");
        when(manager.getRunningOperators(any(), any())).thenReturn(expected);

        String begin = "1000";
        String end = "1300";
        ResponseEntity response = this.operatorController.getRunningOperators(begin, end);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void givenValidParameters_whenGetRunningOperator_whenNoOperatorsFound_shouldReturnStatus404() {
        when(manager.getRunningOperators(any(), any())).thenReturn(List.of());

        String begin = "1000";
        String end = "1300";
        ResponseEntity response = this.operatorController.getRunningOperators(begin, end);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void givenInvalidBeginDate_whenGetRunningOperator_shouldReturnStatus400() {
        String begin = null;
        String end = "1200";
        ResponseEntity response = this.operatorController.getRunningOperators(begin, end);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void givenInvalidEndDate_whenGetRunningOperator_shouldReturnStatus400() {
        String begin = "1300";
        String end = null;
        ResponseEntity response = this.operatorController.getRunningOperators(begin, end);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void givenValidParameters_whenGetVehiclesIdsFromAnOperator_shouldReturnVehicleId() {
        List<String> expected = List.of("1", "2");

        ResponseEntity response = getVehiclesIdsFromAnOperator(expected);

        assertLinesMatch(expected, (List<String>) response.getBody());
    }

    @Test
    public void givenValidParameters_whenGetVehiclesIdsFromAnOperator_shouldReturnStatus200() {
        List<String> expected = List.of("1", "2");

        ResponseEntity response = getVehiclesIdsFromAnOperator(expected);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private ResponseEntity getVehiclesIdsFromAnOperator(List<String> expected) {
        when(manager.getVehiclesIds(any(), any(), any(), any())).thenReturn(expected);

        String begin = "1000";
        String end = "1300";
        String filters = "atStop";
        String operatorId = "1";

        ResponseEntity response = this.operatorController.getVehiclesIdsFromAnOperator(operatorId, begin, end, filters);
        return response;
    }

    @Test
    public void givenValidParameters_whenGetVehiclesIdsFromAnOperator_whenNoVehiclesFound_shouldReturnStatus404() {
        when(manager.getVehiclesIds(any(), any(), any(), any())).thenReturn(List.of());

        String begin = "1000";
        String end = "1300";
        String filters = "atStop";
        String operatorId = "1";

        ResponseEntity response = this.operatorController.getVehiclesIdsFromAnOperator(operatorId, begin, end, filters);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void givenInvalidBeginDate_whenGetVehiclesIdsFromAnOperator_shouldReturnStatus400() {
        String begin = null;
        String end = "1300";
        String filters = "atStop";
        String operatorId = "1";

        ResponseEntity response = this.operatorController.getVehiclesIdsFromAnOperator(operatorId, begin, end, filters);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void givenInvalidEndDate_whenGetVehiclesIdsFromAnOperator_shouldReturnStatus400() {
        String begin = "1500";
        String end = null;
        String filters = "atStop";
        String operatorId = "1";

        ResponseEntity response = this.operatorController.getVehiclesIdsFromAnOperator(operatorId, begin, end, filters);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void givenInvalidFilters_whenGetVehiclesIdsFromAnOperator_shouldReturnStatus400() {
        String begin = "1500";
        String end = "1400";
        String filters = "ataStop";
        String operatorId = "1";

        ResponseEntity response = this.operatorController.getVehiclesIdsFromAnOperator(operatorId, begin, end, filters);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
