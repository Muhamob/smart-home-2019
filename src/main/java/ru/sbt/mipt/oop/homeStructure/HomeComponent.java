package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.List;

public interface HomeComponent {
    public SmartDevice getSmartDevice(String id);
    public List<SmartDevice> getAllSmartDevices();
    public boolean execute(HomeComponentAction action);
    public boolean contains(String id);
}
