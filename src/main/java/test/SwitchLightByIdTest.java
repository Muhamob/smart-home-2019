package test;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.eventHandlers.EventList;
import ru.sbt.mipt.oop.homeStructure.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SwitchLightByIdTest {
    @Test
    public void turnOnOneLight() {
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

        floor.setRooms(Collections.singletonList(kitchen));
        interior.setFloors(Collections.singletonList(floor));

        SmartHome home = new SmartHome(premises);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "5");
        EventList.run(home, event);

        assertTrue(((Light) home.getSmartDevice("5")).isOn());

        for (int i=1; i < 5; i++) {
            assertFalse(((Light) home.getSmartDevice(Integer.toString(i))).isOn());
        }
    }
}
