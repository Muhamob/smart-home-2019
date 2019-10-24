package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.homeStructure.HomeComponent;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;


public class SwitchAllDoorsState implements HomeComponentAction {

    private final SensorEventType eventType;

    public SwitchAllDoorsState(SensorEvent event) {
        this.eventType = event.getType();
    }

    @Override
    public boolean execute(HomeComponent homeComponent) {
        if (eventType != DOOR_OPEN && eventType != DOOR_CLOSED) return false;

        if (!(homeComponent instanceof Door)) return false;

        Door door = (Door) homeComponent;

        if (eventType == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " is now opened");
        } else {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " is now closed");
        }

        return true;

    }
}
