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

class SwitchDoorStateByIdTest {

    @Test
    void testCloseSomeDoor() {

        Interior interior = new Interior();
        List<HomeComponent> premises = Collections.singletonList(interior);

        Floor floor = new Floor(0);

        List<SmartDevice> kitchenDevices = Arrays.asList(
                new Door("1", true),
                new Door("2", false),
                new Door("3", false),
                new Door("4", false),
                new Door("5", false)
        );
        Room kitchen = new Room("kitchen", kitchenDevices);

        floor.setRooms(Collections.singletonList(kitchen));
        interior.setFloors(Collections.singletonList(floor));

        SmartHome home = new SmartHome(premises);

        boolean[] doorStates = new boolean[4];
        Door door_;
        for (int i = 0; i < doorStates.length; i++) {
            String id = Integer.toString(i+1+1);
            home.execute(x -> {
                if (!(x instanceof Door)) return false;

                Door door = (Door) x;
                if (door.getId().equals(id)) {
                    assertFalse(door.isOpen());
                    return true;
                }

                return false;
            });
        }

        // close door with id 1
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        EventList.run(home, event);

        home.execute(x -> {
            if (!(x instanceof Door)) return false;

            Door door = (Door) x;
            if (door.getId().equals("1")) {
                assertFalse(door.isOpen());
                return true;
            }

            return false;
        });

        for (int i = 0; i < doorStates.length; i++) {
            String id = Integer.toString(i+1+1);
            home.execute(x -> {
                if (!(x instanceof Door)) return false;

                Door door = (Door) x;
                if (door.getId().equals(id)) {
                    assertFalse(door.isOpen());
                    return true;
                }

                return false;
            });
        }
    }

    @Test
    void testCloseHallDoor() {
        Interior interior = new Interior();
        List<HomeComponent> premises = Collections.singletonList(interior);

        Floor floor = new Floor(0);

        List<SmartDevice> kitchenDevices = Arrays.asList(
                new Light("1", true),
                new Light("2", false),
                new Light("3", true),
                new Light("4", false),
                new Light("5", true)
        );
        Room kitchen = new Room("kitchen", kitchenDevices);

        List<SmartDevice> hallDevices = Arrays.asList(
                new Light("6", true),
                new Light("7", true),
                new Light("8", true),
                new Door("9", true)
        );
        Room hall = new Room("hall", hallDevices);

        floor.setRooms(Arrays.asList(kitchen, hall));
        interior.setFloors(Collections.singletonList(floor));

        SmartHome home = new SmartHome(premises);

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "9");
        EventList.run(home, event);

        home.execute(x -> {
            if (x instanceof Light) {
                assertFalse(((Light) x).isOn());
                return true;
            }
            return false;
        });
    }
}