package ru.sbt.mipt.oop.homeUtils;

import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.List;

public class HomeCheckers {
    public static boolean checkContainsOnlyUniqueSmartDevice(List<SmartDevice> devices) {
        int foundSmartDevices = 0;

        for (SmartDevice device : devices) {
            if (device != null) {
                foundSmartDevices += 1;
            }
        }

        return foundSmartDevices <= 1;
    }

    public static SmartDevice getFirstNotNull(List<SmartDevice> devices) {
        for (SmartDevice device : devices) {
            if (device != null) {
                return device;
            }
        }

        return null;
    }
}
