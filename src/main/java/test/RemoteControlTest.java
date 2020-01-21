package test;

import org.junit.jupiter.api.Test;
import rc.RemoteControl;
import ru.sbt.mipt.oop.devices.Door;
import ru.sbt.mipt.oop.devices.Light;
import ru.sbt.mipt.oop.devices.SmartDevice;
import ru.sbt.mipt.oop.devices.alarm.Alarm;
import ru.sbt.mipt.oop.devices.alarm.AlarmActivated;
import ru.sbt.mipt.oop.devices.alarm.AlarmAlert;
import ru.sbt.mipt.oop.homeStructure.Room;
import ru.sbt.mipt.oop.homeStructure.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RemoteControlTest {
    String hallDoorId = "1";

    SmartHome getTestSmartHome() {
        List<SmartDevice> hallDevices = Arrays.asList(
                new Door(hallDoorId, true),
                new Light("2", false),
                new Alarm("911")
        );
        Room hall = new Room("hall", hallDevices);

        List<SmartDevice> kitchenDevices = Arrays.asList(
                new Door("3", false),
                new Light("4", false),
                new Light("5", false),
                new Door("3", false)
        );
        Room kitchen = new Room("kitchen", kitchenDevices);

        return new SmartHome(Arrays.asList(hall, kitchen));
    }

    @Test
    void testBindRcKeys() {
        SmartHome smartHome = getTestSmartHome();
        BrandNewRemoteControl rc = new BrandNewRemoteControl();
        rc.addRemoteControl("A", new CloseMainDoorCommand(smartHome));
    }

    @Test
    void testActivateAlarmCommand() {
        SmartHome smartHome = getTestSmartHome();
        Alarm alarm = smartHome.getAlarm();

        BrandNewRemoteControl rc = new BrandNewRemoteControl();
        rc.addRemoteControl("1", new ActivateAlarmCommand(smartHome, "9999"));
        rc.onButtonPressed("1", "1");

        assertTrue(alarm.getAlarmState() instanceof AlarmActivated);
    }

    @Test
    void testInvokeAlarmCommand() {
        SmartHome smartHome = getTestSmartHome();
        Alarm alarm = smartHome.getAlarm();

        BrandNewRemoteControl rc = new BrandNewRemoteControl();
        rc.addRemoteControl("1", new ActivateAlertCommand(smartHome));
        rc.onButtonPressed("1", "1");

        assertTrue(alarm.getAlarmState() instanceof AlarmAlert);
    }

    @Test
    void testCloseMainDoorCommand() {
        SmartHome smartHome = getTestSmartHome();

        BrandNewRemoteControl rc = new BrandNewRemoteControl();
        rc.addRemoteControl("1", new CloseMainDoorCommand(smartHome));
        rc.onButtonPressed("1", "1");

        boolean result = smartHome.execute(actionable -> {
            if (actionable instanceof Door) {
                Door door = (Door) actionable;
                return door.getId().equals(hallDoorId);
            }
            return false;
        });

        assertTrue(result);
    }

    @Test
    void testTurnHallLightsOnCommand() {
        SmartHome smartHome = getTestSmartHome();

        BrandNewRemoteControl rc = new BrandNewRemoteControl();
        rc.addRemoteControl("1", new TurnHallLightsOnCommand(smartHome));
        rc.onButtonPressed("1", "1");

        smartHome.execute(actionable -> {
            if (actionable instanceof Room) {
                Room room = (Room) actionable;
                if (room.getName().equals("hall")) {
                    room.execute(actionable1 -> {
                        if (actionable1 instanceof Light) {
                            Light light = (Light) actionable1;
                            assertTrue(light.isOn());
                        }
                        return true;
                    });
                }
            }
            return true;
        });
    }

    @Test
    void testTurnOffAllLightsCommand() {
        SmartHome smartHome = getTestSmartHome();

        BrandNewRemoteControl rc = new BrandNewRemoteControl();
        rc.addRemoteControl("1", new TurnOffAllLightsCommand(smartHome));
        rc.onButtonPressed("1", "1");

        smartHome.execute(actionable -> {
            if (actionable instanceof Light) {
                Light light = (Light) actionable;
                assertFalse(light.isOn());
            }
            return true;
        });
    }

    @Test
    void testTurnOnAllLightsCommand() {
        SmartHome smartHome = getTestSmartHome();

        BrandNewRemoteControl rc = new BrandNewRemoteControl();
        rc.addRemoteControl("1", new TurnOnAllLightsCommand(smartHome));
        rc.onButtonPressed("1", "1");

        smartHome.execute(actionable -> {
            if (actionable instanceof Light) {
                Light light = (Light) actionable;
                assertTrue(light.isOn());
            }
            return true;
        });
    }
}
