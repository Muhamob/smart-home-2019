package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.devices.SmartDevice;

public interface HomeComponent {
    public SmartDevice getSmartDevice(String id);

    static boolean checkContainsOnlyUniqueSmartDevice(SmartDevice... devices) {
        int foundSmartDevices = 0;

        for (SmartDevice device : devices) {
            if (device != null) {
                foundSmartDevices += 1;
            }
        }

        return foundSmartDevices <= 1;
    }
}
