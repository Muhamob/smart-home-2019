package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Door getDoor(String id) {
        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(id)) {
                    return door;
                }
            }
        }
        return null;
    }
}
