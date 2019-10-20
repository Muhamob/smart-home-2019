package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

public class EventList {
    public static void run(SmartHome smartHome, SensorEvent event) {
        for (ActionEnum eventAction : ActionEnum.values()) {
            HomeComponentAction action = eventAction.getAction(event.getType(), event.getObjectId());
            smartHome.execute(action);
        }
    }
}
