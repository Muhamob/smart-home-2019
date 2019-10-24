package test;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.actions.SwitchAllLights;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.homeStructure.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwitchAllLightsTest {
    @Test
    public void turnOnAllLight() {
        Interior interior = new Interior();
        List<HomeComponent> premises = Collections.singletonList(interior);

        Floor floor = new Floor(0);

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

        floor.setRooms(Arrays.asList(kitchen, hall));
        interior.setFloors(Collections.singletonList(floor));

        SmartHome home = new SmartHome(premises);

        home.execute(new SwitchAllLights(new SensorEvent(SensorEventType.LIGHT_ON, null)));

        for (SmartDevice device : home.getAllSmartDevices()) {
            Light light = (Light) device;
            assertTrue(light.isOn());
        }
    }
}
