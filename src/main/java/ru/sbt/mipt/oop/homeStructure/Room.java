package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room implements HomeComponent {
    // возможно лучше будет HashMap
    private Map<String, SmartDevice> smartDevices;
    private String name;

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
        device.setRoom(this);
        this.smartDevices.put(device.getId(), device);
    }

    public String getName() {
        return name;
    }
}
