package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.*;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.homeStructure.Room;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventDoor implements EventHandler {

    @Override
    public void run(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            Door door = (Door) smartHome.getSmartDevice(event.getObjectId());
            // событие от двери
            if (door != null) {
                if (event.getType() == DOOR_OPEN) {
                    door.setOpen(true);
//                    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                } else {
                    door.setOpen(false);
                    Room room = door.getRoom();
                    System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                    // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)

                    // to implement
                    /*
                    if (room.getName().equals("hall")) {
                        for (Room homeRoom : smartHome.getRooms()) {
                            for (Light light : homeRoom.getLights()) {
                                light.setOn(false);
                            }
                        }
                    }
                    */
                }
            }
        }
    }
}
