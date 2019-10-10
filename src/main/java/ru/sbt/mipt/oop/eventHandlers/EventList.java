package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

import java.util.Arrays;
import java.util.List;

public class EventList {
    private static List<EventHandler> eventHandlers = Arrays.asList(
            new EventLight(),
            new EventDoor());

    public static void run(SmartHome smartHome, SensorEvent event) {
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.run(smartHome, event);
        }
    }
}
