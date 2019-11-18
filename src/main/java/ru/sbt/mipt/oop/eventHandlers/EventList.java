package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class EventList {
    public static void run(SmartHome smartHome, SensorEvent event) {
        List<HomeComponentAction> actions = EventCollectionCreator.getActionList(event);

        for (HomeComponentAction action : actions) {
            smartHome.execute(action);
        }
    }
}
