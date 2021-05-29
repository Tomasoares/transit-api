package com.exercise.transitapi.util;

public enum Filters {

    AT_STOP("atStop");

    private final String paramString;

    Filters(String paramString) {
        this.paramString = paramString;
    }

    public String getParamString() {
        return this.paramString;
    }
}
