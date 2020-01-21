package ru.sbt.mipt.oop.devices;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.homeStructure.Actionable;

public class SmartDevice implements Actionable {
    private final String id;

    public SmartDevice(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean execute(HomeComponentAction action) {
        return action.execute(this);
    }
}
