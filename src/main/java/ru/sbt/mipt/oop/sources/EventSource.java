package ru.sbt.mipt.oop.sources;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventSource {
    public SensorEvent getNextSensorEvent();
}
