package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.homeStructure.Room;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

public class CloseMainDoorCommand implements Command {
    private final SmartHome smartHome;

    public CloseMainDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(actionable -> {
            boolean result = false;

            if (actionable instanceof Room) {
                Room room = (Room) actionable;
                if (room.getName().equals("Hall")) {
                    result = room.execute(object_new -> {
                            Door door = (Door) object_new;
                            door.setOpen(true);
                            return true;
                        }
                    );
                }
            }
            return result;
        });
    }
}
