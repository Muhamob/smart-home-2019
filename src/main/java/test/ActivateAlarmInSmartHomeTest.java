package test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.devices.*;
import ru.sbt.mipt.oop.devices.alarm.Alarm;
import ru.sbt.mipt.oop.devices.alarm.AlarmActivated;
import ru.sbt.mipt.oop.devices.alarm.AlarmAlert;
import ru.sbt.mipt.oop.devices.alarm.AlarmDeactivated;
import ru.sbt.mipt.oop.eventHandlers.EventList;
import ru.sbt.mipt.oop.homeStructure.*;
import ru.sbt.mipt.oop.utils.HomeUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ActivateAlarmInSmartHomeTest {
    SmartHome createSmartHomeWithAlarmAndSMSSender() {

        List<SmartDevice> kitchenDevices = Arrays.asList(
                new Light("1", false),
                new Door("2", false),
                new Alarm("10"),
                new SMSSender("123", "911")
        );
        Room kitchen = new Room("kitchen", kitchenDevices);

        return new SmartHome(Collections.singletonList(kitchen));
    }

    @Test
    void checkAlarmActivates() {
        SmartHome home = createSmartHomeWithAlarmAndSMSSender();
        Alarm alarm = home.getAlarm(); //home.getAlarm();

        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);

        SensorEvent event = new SensorEvent(SensorEventType.ALARM_ACTIVATE.setCode("1000"), "10");

        EventList.run(home, event);
        alarm = home.getAlarm(); //home.getAlarm();
        assertEquals(alarm.getAlarmState().getClass(), AlarmActivated.class);
    }

    @Test
    void checkAlarmDeactivatesWithCorrectPassword() {
        SmartHome home = createSmartHomeWithAlarmAndSMSSender();
        Alarm alarm = home.getAlarm(); //home.getAlarm();
        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);

        SensorEvent eventActivateAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE.setCode("1000"), "10");

        EventList.run(home, eventActivateAlarm);
        alarm = home.getAlarm(); //home.getAlarm();
        assertEquals(alarm.getAlarmState().getClass(), AlarmActivated.class);

        SensorEvent eventDeactivateAlarm = new SensorEvent(SensorEventType.ALARM_DEACTIVATE.setCode("1000"), "10");

        EventList.run(home, eventDeactivateAlarm);
        alarm = home.getAlarm(); //home.getAlarm();
        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);
    }

    @Test
    void checkAlarmAlertWithWrongPassword() {
        SmartHome home = createSmartHomeWithAlarmAndSMSSender();
        Alarm alarm = home.getAlarm();
        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);

        SensorEvent eventActivateAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE.setCode("1000"), "10");

        EventList.run(home, eventActivateAlarm);
        alarm = home.getAlarm();
        System.out.println("checkAlarmAlertWithWrongPassword: " + alarm.getAlarmState());
        assertEquals(alarm.getAlarmState().getClass(), AlarmActivated.class);

        // В данном случае приходит событие о деактивации сигнализации с заведомо ложным паролем
        SensorEvent eventDeactivateAlarm = new SensorEvent(SensorEventType.ALARM_DEACTIVATE.setCode("1001"), "10");

        EventList.run(home, eventDeactivateAlarm);
        alarm = home.getAlarm();
        assertEquals(alarm.getAlarmState().getClass(), AlarmAlert.class);
    }

    @Test
    void sendingSMSWhileActionWithActivatedAlarm() {
        SmartHome home = createSmartHomeWithAlarmAndSMSSender();

        SensorEvent eventActivateAlarm = new SensorEvent(SensorEventType.ALARM_ACTIVATE.setCode("1000"), "10");
        EventList.run(home, eventActivateAlarm);

        SensorEvent turnOnLight = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        EventList.run(home, turnOnLight);

        SensorEvent turnOffLight = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        EventList.run(home, turnOffLight);
    }

}
