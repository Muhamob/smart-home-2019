package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.devices.SmartDevice;

import java.util.List;

public class HomeCheckers {

    public static SmartDevice getFirstNotNull(List<SmartDevice> devices) {
        for (SmartDevice device : devices) {
            if (device != null) {
                return device;
            }
        }

        return null;
    }
}
