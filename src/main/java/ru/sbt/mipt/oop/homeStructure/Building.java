package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeUtils.HomeCheckers;

import java.util.Arrays;
import java.util.List;

public class Building implements HomeComponent {
    private Interior interior;
    private Exterior exterior;

    public Building(Interior interior, Exterior exterior) {
        this.interior = interior;
        this.exterior = exterior;
    }

    /*
            Добавить исключение, если найдено несколько устройств с одинаковым ID
             */
    @Override
    public SmartDevice getSmartDevice(String id) {
        System.out.println(interior);
        System.out.println(exterior);
        SmartDevice smartDeviceInterior = interior.getSmartDevice(id);
        SmartDevice smartDeviceExterior = exterior.getSmartDevice(id);

        List<SmartDevice> foundDevices = Arrays.asList(smartDeviceExterior, smartDeviceInterior);

        return HomeCheckers.getFirstNotNull(foundDevices);
    }
}
