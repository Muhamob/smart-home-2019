package ru.sbt.mipt.oop;

public enum SensorEventType {
    LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSED, ALARM_ACTIVATE, ALARM_DEACTIVATE;

    private String code;

    public SensorEventType setCode(String code) {
        this.code = code;
        return this;
    }

    public String getCode() {
        return code;
    }
}
