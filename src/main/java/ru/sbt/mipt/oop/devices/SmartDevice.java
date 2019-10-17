package ru.sbt.mipt.oop.devices;

import ru.sbt.mipt.oop.homeStructure.Room;

public class SmartDevice {
    private final String id;
    private Room room;

    public SmartDevice(String id) {
        this.id = id;
        this.room = null;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public String getId() {
        return id;
    }
}
