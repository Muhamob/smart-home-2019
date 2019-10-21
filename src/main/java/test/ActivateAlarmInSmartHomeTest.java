package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.*;
import ru.sbt.mipt.oop.eventHandlers.EventList;
import ru.sbt.mipt.oop.homeStructure.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActivateAlarmInSmartHomeTest {
    SmartHome createSmartHomeWithAlarm() {
        Interior interior = new Interior();
        List<HomeComponent> premises = Collections.singletonList(interior);

        Floor floor = new Floor(0);

        List<SmartDevice> kitchenDevices = Arrays.asList(
                new Light("1", false),
                new Door("2", false),
                new Alarm("10")
        );
        Room kitchen = new Room("kitchen", kitchenDevices);

        floor.setRooms(Collections.singletonList(kitchen));
        interior.setFloors(Collections.singletonList(floor));

        return new SmartHome(premises);
    }

    @Test
    void checkAlarmActivates() {
        SmartHome home = createSmartHomeWithAlarm();
        Alarm alarm = (Alarm) home.getSmartDevice("10");
        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);

        SensorEvent event = new SensorEvent(SensorEventType.ALARM_ACTIVATE.setCode("1000"), "10");

        EventList.run(home, event);
        alarm = (Alarm) home.getSmartDevice("10");
        assertEquals(alarm.getAlarmState().getClass(), AlarmActivated.class);
    }

    @Test
    void checkAlarmDeactivatesWithCorrectPassword() {
        SmartHome home = createSmartHomeWithAlarm();
        Alarm alarm = (Alarm) home.getSmartDevice("10");
        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);

        SensorEvent eventActivateAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE.setCode("1000"), "10");

        EventList.run(home, eventActivateAlarm);
        alarm = (Alarm) home.getSmartDevice("10");
        assertEquals(alarm.getAlarmState().getClass(), AlarmActivated.class);

        SensorEvent eventDeactivateAlarm = new SensorEvent(SensorEventType.ALARM_DEACTIVATE.setCode("1000"), "10");

        EventList.run(home, eventDeactivateAlarm);
        alarm = (Alarm) home.getSmartDevice("10");
        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);
    }

    @Test
    void checkAlarmDeactivatesWithWrongPassword() {
        SmartHome home = createSmartHomeWithAlarm();
        Alarm alarm = (Alarm) home.getSmartDevice("10");
        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);

        SensorEvent eventActivateAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE.setCode("1000"), "10");

        EventList.run(home, eventActivateAlarm);
        alarm = (Alarm) home.getSmartDevice("10");
        assertEquals(alarm.getAlarmState().getClass(), AlarmActivated.class);

        // В данном случае приходит событие о деактивации сигнализации с заведомо ложным паролем
        SensorEvent eventDeactivateAlarm = new SensorEvent(SensorEventType.ALARM_DEACTIVATE.setCode("1001"), "10");

        EventList.run(home, eventDeactivateAlarm);
        alarm = (Alarm) home.getSmartDevice("10");
        assertEquals(alarm.getAlarmState().getClass(), AlarmAlert.class);
    }

}
