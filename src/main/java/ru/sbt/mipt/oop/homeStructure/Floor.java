package ru.sbt.mipt.oop.homeStructure;

import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeUtils.HomeCheckers;

import java.util.ArrayList;
import java.util.List;

public class Floor implements HomeComponent {
    private int floor;
    private List<Room> rooms;

    public Floor(int floor) {
        this.floor = floor;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean execute(HomeComponentAction action) {
        boolean executed = action.execute(this);
        for (Room room : rooms) {
            executed |= room.execute(action);
        }

        return executed;
    }

    @Override
    public boolean contains(String id) {
        for (Room room : rooms) {
            if (room.contains(id)) return true;
        }
        return false;
    }
}
