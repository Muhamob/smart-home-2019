package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

public interface EventHandler {
    public void run(SmartHome smartHome, SensorEvent event);
}
