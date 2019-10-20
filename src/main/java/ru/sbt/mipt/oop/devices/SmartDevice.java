package ru.sbt.mipt.oop.devices;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.homeStructure.HomeComponent;

import java.util.Collections;
import java.util.List;

public class SmartDevice implements HomeComponent {
    private final String id;

    public SmartDevice(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public SmartDevice getSmartDevice(String id) {
        return this;
    }

    @Override
    public List<SmartDevice> getAllSmartDevices() {
        return Collections.singletonList(this);
    }

    @Override
    public void execute(HomeComponentAction action) {
        action.execute(this);
    }
}
