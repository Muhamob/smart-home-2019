package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.actions.DoorScenario;
import ru.sbt.mipt.oop.actions.SwitchLightById;

import java.util.Arrays;
import java.util.List;

public class ActionsConfig {
    private static final List<Class> actions = Arrays.asList(
            DoorScenario.class,
            SwitchLightById.class
    );

    public static List<Class> getActions() {
        return actions;
    }
}
