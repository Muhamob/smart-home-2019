package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.*;

public class Room implements HomeComponent {
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

    @Override
    public SmartDevice getSmartDevice(String id) {
        return smartDevices.get(id);
    }

    public void addDevice(SmartDevice device) {
        this.smartDevices.put(device.getId(), device);
    }

    public String getName() {
        return name;
    }

    @Override
    public List<SmartDevice> getAllSmartDevices() {
        return new ArrayList<>(this.smartDevices.values());
    }

    @Override
    public void execute(HomeComponentAction action) {
        action.execute(this);
        for (SmartDevice device : smartDevices.values()) {
            device.execute(action);
        }
    }
}
