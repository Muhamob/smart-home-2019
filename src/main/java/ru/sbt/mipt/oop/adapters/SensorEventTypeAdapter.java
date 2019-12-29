package ru.sbt.mipt.oop.adapters;

import ru.sbt.mipt.oop.SensorEventType;

public enum SensorEventTypeAdapter {
    LightIsOn(SensorEventType.LIGHT_ON),
    LightIsOff(SensorEventType.LIGHT_OFF),
    DoorIsOpen(SensorEventType.DOOR_OPEN),
    DoorIsClosed(SensorEventType.DOOR_CLOSED),
    DoorIsLocked(SensorEventType.DOOR_OPEN),
    DoorIsUnlocked(SensorEventType.DOOR_CLOSED),;

    private SensorEventType sensorEventType;

    SensorEventTypeAdapter(SensorEventType sensorEventType) {
        this.sensorEventType = sensorEventType;
    }

    public SensorEventType getSensorEventType() {
        return sensorEventType;
    }
}
