package test;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.actions.SwitchAllLights;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.eventHandlers.EventList;
import ru.sbt.mipt.oop.homeStructure.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwitchAllLightsTest {
    @Test
    public void turnOnAllLight() {

        List<SmartDevice> kitchenDevices = Arrays.asList(
                new Light("1", false),
                new Light("2", false),
                new Light("3", false),
                new Light("4", false),
                new Light("5", false)
        );
        Room kitchen = new Room("kitchen", kitchenDevices);

        List<SmartDevice> hallDevices = Arrays.asList(
                new Light("6", false),
                new Light("7", false),
                new Light("8", false),
                new Light("9", false),
                new Light("10", false)
        );
        Room hall = new Room("hall", hallDevices);

        SmartHome home = new SmartHome(Arrays.asList(kitchen, hall));

        home.execute(new SwitchAllLights(new SensorEvent(SensorEventType.LIGHT_ON, null)));

        for (int i=1; i<11; i++) {
            String id = Integer.toString(i);
            home.execute(x->{
                if (x instanceof Light) {
                    Light light = (Light) x;
                    if (light.getId().equals(id)) {
                        assertTrue(light.isOn());
                        return true;
                    }
                }
                return false;
            });
        }
    }
}
