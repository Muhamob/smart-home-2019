package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.homeStructure.HomeComponent;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class SwitchDoorStateById implements HomeComponentAction {
    private final SensorEventType eventType;
    private final String id;

    public SwitchDoorStateById(SensorEventType eventType, String id) {
        this.eventType = eventType;
        this.id = id;
    }

    @Override
    public boolean execute(HomeComponent homeComponent) {
        if (!(eventType == DOOR_OPEN || eventType == DOOR_CLOSED)) return false;

        if (!(homeComponent instanceof Door)) return false;

        Door door = (Door) homeComponent;

        if (door.getId().equals(id)) {
            if (eventType == DOOR_OPEN) {
                door.setOpen(true);
                System.out.println("Door " + id + " is now opened");
            } else {
                door.setOpen(false);
                System.out.println("Door " + id + " is now closed");
            }
            return true;
        }

        return false;
    }
}
