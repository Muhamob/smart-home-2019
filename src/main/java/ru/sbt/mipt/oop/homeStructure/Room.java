package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.*;

public class Room implements HomeComponent {
    // возможно лучше будет HashMap
    private Map<String, SmartDevice> smartDevices;
    private String name;

    public Room(String name) {
        smartDevices = new HashMap<>();
        this.name = name;
    }

    public Room(Collection<SmartDevice> smartDevices, String name) {
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

    public void removeDevice(String id) {
        this.smartDevices.remove(id);
    }

    public String getName() {
        return name;
    }

    @Override
    public List<SmartDevice> getAllSmartDevices() {
        List<SmartDevice> smartDevices = new ArrayList<>(this.smartDevices.values());

        return smartDevices;
    }
}
