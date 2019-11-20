package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.*;

public class Room implements Actionable {
    private Map<String, SmartDevice> smartDevices;
    private String name;

    public Room(String name) {
        smartDevices = new HashMap<>();
        this.name = name;
    }

    public Room(String name, Collection<SmartDevice> smartDevices) {
        this.smartDevices = new HashMap<>();
        for (SmartDevice device : smartDevices) {
            addDevice(device);
        }
        this.name = name;
    }

    public void addDevice(SmartDevice device) {
        this.smartDevices.put(device.getId(), device);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean execute(HomeComponentAction action) {
        boolean executed = action.execute(this);
        for (SmartDevice device : smartDevices.values()) {
            executed |= device.execute(action);
        }

        return executed;
    }

    @Override
    public boolean contains(String id) {
        for (SmartDevice device_ : smartDevices.values()) {
            if (device_.contains(id)) return true;
        }
        return false;
    }
}
