package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.homeStructure.Room;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

public class TurnHallLightsOnCommand implements Command {
    private final SmartHome smartHome;

    public TurnHallLightsOnCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(actionable -> {
            boolean result = false;

            if (actionable instanceof Room) {
                Room room = (Room) actionable;
                if (room.getName().equals("hall")) {
                    result = room.execute(object_new -> {
                        if (object_new instanceof Light) {
                            Light light = (Light) object_new;
                            light.setOn(true);
                            return true;
                        }
                        return false;
                    });
                }
            }

            return result;
        });
    }
}
