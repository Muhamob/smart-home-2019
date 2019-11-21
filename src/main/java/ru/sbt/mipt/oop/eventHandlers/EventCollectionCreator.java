package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.actions.DoorScenario;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.actions.SwitchLightById;

import java.util.Arrays;
import java.util.List;

public class EventCollectionCreator {
    public static List<HomeComponentAction> getActionList(SensorEvent event) {
        return Arrays.asList(
                new DoorScenario(event),
                new SwitchLightById(event),
                new AlarmAction(event)
        );
    }
}
