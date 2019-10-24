package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import java.lang.reflect.InvocationTargetException;

public class EventList {
    public static void run(SmartHome smartHome, SensorEvent event) {

        for (Class eventActionClass : ActionsConfig.getActions()) {
            HomeComponentAction action = null;
            try {
                action = (HomeComponentAction) eventActionClass.getConstructor(SensorEvent.class).newInstance(event);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }

            assert action != null;
            smartHome.execute(action);
        }
    }
}
