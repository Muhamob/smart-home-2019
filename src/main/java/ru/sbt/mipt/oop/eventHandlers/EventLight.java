package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.homeStructure.Room;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homeStructure.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;


public class EventLight implements EventHandler {

    @Override
    public void run(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            Light light = (Light) smartHome.getSmartDevice(event.getObjectId());
            if (light != null) {
                Room room = smartHome.getRoom(light.getId());
                if (event.getType() == LIGHT_ON) {
                    light.setOn(true);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                } else {
                    light.setOn(false);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                }
            }
        }
    }
}
