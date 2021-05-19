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

        String begin = "10:00";
        String end = "13:00";
        ResponseEntity response = this.operatorController.getRunningOperators(begin, end);

        assertLinesMatch(expected, (List<String>) response.getBody());
    }

    @Test
    public void givenValidParameters_whenGetRunningOperator_shouldReturnStatus200() {
        List<String> expected = List.of("1", "2");
        when(manager.getRunningOperators(any(), any())).thenReturn(expected);

        String begin = "10:00";
        String end = "13:00";
        ResponseEntity response = this.operatorController.getRunningOperators(begin, end);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void givenValidParameters_whenGetRunningOperator_whenNoOperatorsFound_shouldReturnStatus404() {
        when(manager.getRunningOperators(any(), any())).thenReturn(List.of());

        String begin = "10:00";
        String end = "13:00";
        ResponseEntity response = this.operatorController.getRunningOperators(begin, end);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }



}
