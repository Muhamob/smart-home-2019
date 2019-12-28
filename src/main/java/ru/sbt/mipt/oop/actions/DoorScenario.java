package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventInterface;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.homeStructure.Actionable;
import ru.sbt.mipt.oop.homeStructure.Room;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorScenario implements HomeComponentAction {
    private final SensorEventType eventType;
    private final String eventId;
    private final SensorEventInterface event;

    public DoorScenario(SensorEventInterface event) {
        this.eventType = event.getType();
        this.eventId = event.getObjectId();
        this.event = event;
    }

    @Override
    public boolean execute(Actionable actionable) {
        if (!(eventType == DOOR_OPEN || eventType == DOOR_CLOSED)) return false;

        if (!(actionable instanceof SmartHome)) return false;

        SmartHome home = (SmartHome) actionable;

        boolean executed = home.execute(homeComponent1 -> {
            if (!(homeComponent1 instanceof Room)) return false;

            Room room = (Room) homeComponent1;
            boolean executed_ = room.execute(new SwitchDoorStateById(event));

            System.out.println("room name " + room.getName());
            System.out.println("event " + event + " " + event.getType() + " " + event.getObjectId());
            // executed means that smart device in that room
            if (executed_ && room.getName().equals("hall")) {
                home.execute(new SwitchAllLights(new SensorEvent(LIGHT_OFF, null)));
                return true;
            }

            return executed_;
        });

        return executed;
    }
}
