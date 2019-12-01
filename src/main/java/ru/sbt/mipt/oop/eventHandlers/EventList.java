package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import java.util.List;

public class EventList {
    public static void run(SmartHome smartHome, SensorEvent event) {
        EventCollectionCreator eventCollectionCreator = new EventCollectionCreator(smartHome);
        List<HomeComponentAction> actions = eventCollectionCreator.getActionList(event);

        for (HomeComponentAction action : actions) {
            // Used in debug mode, don't know how to create proper logger in java
            System.out.println("Action is: " + action);
            smartHome.execute(action);
        }
    }
}
