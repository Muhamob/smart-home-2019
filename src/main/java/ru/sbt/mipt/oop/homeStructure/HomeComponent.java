package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.List;

public interface HomeComponent {
    public SmartDevice getSmartDevice(String id);
    public List<SmartDevice> getAllSmartDevices();
    public void execute(HomeComponentAction action);
}
