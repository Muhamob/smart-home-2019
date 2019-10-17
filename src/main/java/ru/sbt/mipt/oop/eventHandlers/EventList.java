package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public class EventList {
    public static void run(SmartHome smartHome, SensorEvent event) {
        for (EventHandlerEnum eventHandlerEnum : EventHandlerEnum.values()) {
            EventHandler eventHandler = eventHandlerEnum.getEventHandler();
            eventHandler.run(smartHome, event);
        }
    }
}
