package com.exercise.transitapi.controller;

import com.exercise.transitapi.entities.GPSEntry;
import com.exercise.transitapi.manager.api.OperatorManager;
import com.exercise.transitapi.manager.api.VehicleManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    public VehicleController operatorController;

    @Mock
    public VehicleManager manager;

    public VehicleControllerTest() {
        super();
    }

    @BeforeEach
    public void init() {
        this.operatorController = new VehicleController(this.manager);
    }

    @Test
    public void givenValidParameters_whenGetVehicleTrace_shouldReturnGPSEntry() {
        List<GPSEntry> expected = List.of(new GPSEntry(LocalDateTime.now(),
                "",
                1D,
                1D,
                "",
                "",
                false));

        ResponseEntity response = mockAndGetVehicleTrace(expected);

        assertEquals(expected, response.getBody());
    }

    private ResponseEntity mockAndGetVehicleTrace(List<GPSEntry> expected) {
        when(manager.getVehicleTrace(any(), any(), any())).thenReturn(expected);

        String begin = "1000";
        String end = "1300";
        String vehicleId = "1";
        ResponseEntity response = this.operatorController.getVehicleTrace(vehicleId, begin, end);
        return response;
    }

    @Test
    public void givenValidParameters_whenGetVehicleTrace_shouldReturnStatus200() {
        List<GPSEntry> expected = List.of(new GPSEntry(LocalDateTime.now(),
                "",
                1D,
                1D,
                "",
                "",
                false));

        ResponseEntity response = mockAndGetVehicleTrace(expected);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void givenValidParameters_whenGetVehicleTrace_whenNoOperatorsFound_shouldReturnStatus404() {
        when(manager.getVehicleTrace(any(), any(), any())).thenReturn(List.of());

        String begin = "1000";
        String end = "1300";
        String vehicleId = "1";
        ResponseEntity response = this.operatorController.getVehicleTrace(vehicleId, begin, end);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void givenInvalidBeginDate_whenGetVehicleTrace_shouldReturnStatus400() {
        String begin = null;
        String end = "1300";
        String vehicleId = "1";
        ResponseEntity response = this.operatorController.getVehicleTrace(vehicleId, begin, end);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void givenInvalidEndDate_whenGetVehicleTrace_shouldReturnStatus400() {
        String begin = "1500";
        String end = null;
        String vehicleId = "1";
        ResponseEntity response = this.operatorController.getVehicleTrace(vehicleId, begin, end);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
