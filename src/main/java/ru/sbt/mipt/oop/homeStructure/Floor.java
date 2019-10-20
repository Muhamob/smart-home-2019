package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.ActionableHomeComponent;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeUtils.HomeCheckers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Floor implements HomeComponent {
    private int floor;
    private List<Room> rooms;

    public void setRooms(int floor, List<Room> rooms) {
        this.floor = floor;
        this.rooms = rooms;
    }

    @Override
    public SmartDevice getSmartDevice(String id) {
        List<SmartDevice> smartDevices = new ArrayList<>();
        for (Room room : rooms) {
            smartDevices.add(room.getSmartDevice(id));
        }

        return HomeCheckers.getFirstNotNull(smartDevices);
    }

    @Override
    public List<SmartDevice> getAllSmartDevices() {
        List<SmartDevice> smartDevices = new ArrayList<>();

        for (Room room : rooms) {
            smartDevices.addAll(room.getAllSmartDevices());
        }

        return smartDevices;
    }

    @Override
    public void execute(HomeComponentAction action) {
        action.execute(this);
        for (Room room : rooms) {
            room.execute(action);
        }
    }
}
