package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.homeStructure.HomeComponent;
import ru.sbt.mipt.oop.homeStructure.Room;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class DoorScenario implements HomeComponentAction {
    private final SensorEventType eventType;
    private final String eventId;

    public DoorScenario(SensorEventType eventType, String eventId) {
        this.eventType = eventType;
        this.eventId = eventId;
    }

    @Override
    public boolean execute(HomeComponent homeComponent) {
        if (!(eventType == DOOR_OPEN || eventType == DOOR_CLOSED)) return false;

        if (!(homeComponent instanceof SmartHome)) return false;

        SmartHome home = (SmartHome) homeComponent;

        boolean executed = home.execute(homeComponent1 -> {
            if (!(homeComponent1 instanceof Room)) return false;

            Room room = (Room) homeComponent1;
            boolean executed_ = room.execute(new SwitchDoorStateById(eventType, eventId));

            // executed means that smart device in that room
            if (executed_ && room.getName().equals("hall")) {
                home.execute(new SwitchAllLights(LIGHT_OFF));
                return true;
            }

            return executed_;
        });

        return executed;
    }
}
