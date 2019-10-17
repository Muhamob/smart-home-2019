package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeUtils.HomeCheckers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Interior implements HomeComponent {

    private Collection<Floor> floors;

    public void setFloors(Collection<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public SmartDevice getSmartDevice(String id) {
        List<SmartDevice> smartDevices = new ArrayList<>();
        for (Floor floor : floors) {
            smartDevices.add(floor.getSmartDevice(id));
        }

        return HomeCheckers.getFirstNotNull(smartDevices);
    }
}
