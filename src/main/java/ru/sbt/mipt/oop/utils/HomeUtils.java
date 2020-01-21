package ru.sbt.mipt.oop.utils;

import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeStructure.Actionable;

public class HomeUtils {
    public static SmartDevice getSmartDevice(Actionable actionable, String id) {
        final SmartDevice[] smartDeviceTmp = {null};

        actionable.execute(x -> {
            if (x instanceof SmartDevice) {
                if (((SmartDevice) x).getId().equals(id)) {
                    smartDeviceTmp[0]= (SmartDevice) x;
                    return true;
                }
            }
            return false;
        });

        return smartDeviceTmp[0];
    }

    public static SmartDevice getSmartDevice(Actionable actionable, Class clazz) {
        final SmartDevice[] smartDeviceTmp = {null};

        actionable.execute(x -> {
            if (x instanceof SmartDevice) {
                if (x.getClass().equals(clazz)) {
                    smartDeviceTmp[0]= (SmartDevice) x;
                    return true;
                }
            }
            return false;
        });

        return smartDeviceTmp[0];
    }
}
