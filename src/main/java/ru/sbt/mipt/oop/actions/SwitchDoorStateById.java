package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.homeStructure.Actionable;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class SwitchDoorStateById implements HomeComponentAction {
    private final SensorEventType eventType;
    private final String id;

    public SwitchDoorStateById(SensorEvent event) {
        this.eventType = event.getType();
        this.id = event.getObjectId();
    }

    @Override
    public boolean execute(Actionable actionable) {
        if (!(eventType == DOOR_OPEN || eventType == DOOR_CLOSED)) return false;

        if (!(actionable instanceof Door)) return false;

        Door door = (Door) actionable;

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
