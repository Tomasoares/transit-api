package com.exercise.transitapi.util;

public enum Extension {

    CSV("csv"),
    ZIP("zip"),
    GZ("gz");

    private final String value;

    Extension(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
