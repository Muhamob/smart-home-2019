package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.List;

/*
Not implemented yet
 */
public class Exterior extends Premise implements HomeComponent {
    @Override
    public SmartDevice getSmartDevice(String id) {
        return null;
    }

    @Override
    public List<SmartDevice> getAllSmartDevices() {
        return null;
    }
}
