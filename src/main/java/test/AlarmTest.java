package test;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.devices.Alarm;
import ru.sbt.mipt.oop.devices.AlarmActivated;
import ru.sbt.mipt.oop.devices.AlarmAlert;
import ru.sbt.mipt.oop.devices.AlarmDeactivated;

import static org.junit.jupiter.api.Assertions.*;


class AlarmTest {
    @Test
    void checkAlarmDeactivatedWhileInitialized() {
        Alarm alarm = new Alarm("10");
        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);
    }

    @Test
    void checkActivateAlarm() {
        Alarm alarm = new Alarm("10");
        alarm.activate("1000");
        assertEquals(alarm.getAlarmState().getClass(), AlarmActivated.class);
    }

    @Test
    void checkAlarmDeactivateWithCorrectPassword() {
        Alarm alarm = new Alarm("10");
        alarm.activate("1000");
        assertEquals(alarm.getAlarmState().getClass(), AlarmActivated.class);

        alarm.deactivate("1000");
        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);
    }

    @Test
    void checkAlarmDeactivateWithWrongPassword() {
        Alarm alarm = new Alarm("10");
        alarm.activate("1000");
        assertEquals(alarm.getAlarmState().getClass(), AlarmActivated.class);

        alarm.deactivate("1001");
        assertEquals(alarm.getAlarmState().getClass(), AlarmAlert.class);
    }

    @Test
    void checkDeactivateAlertWithCorrectPassword() {
        Alarm alarm = new Alarm("10");
        alarm.activate("1000");

        // enter wrong code to invoke alarm
        alarm.deactivate("1001");
        assertEquals(alarm.getAlarmState().getClass(), AlarmAlert.class);
        alarm.deactivate("1001");
        assertEquals(alarm.getAlarmState().getClass(), AlarmAlert.class);
        alarm.deactivate("1000");
        assertEquals(alarm.getAlarmState().getClass(), AlarmDeactivated.class);
    }

    @Test
    void checkIfInvokeAlarmWorks() {
        Alarm alarm = new Alarm("10");
        alarm.invokeAlarm();
        assertEquals(alarm.getAlarmState().getClass(), AlarmAlert.class);
    }
}
