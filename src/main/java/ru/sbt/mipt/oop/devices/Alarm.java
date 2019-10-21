package ru.sbt.mipt.oop.devices;

public class Alarm extends SmartDevice {
    private AlarmState alarmState = new AlarmDeactivated(null);
    private String code;

    public Alarm(String id) {
        super(id);
    }

    public void activate(String code) {
        alarmState = alarmState.activate(code);
    }

    public AlarmState getAlarmState() {
        return alarmState;
    }

    /*
    false - в случае если сработала сигнализация
    true - код верен, сигнализация успешно установлена
     */
    public boolean deactivate(String code) {
        alarmState = alarmState.deactivate(code);
        return !(alarmState instanceof AlarmAlert);
    }

    public void invokeAlarm() {
        alarmState = alarmState.invokeAlert();
    }
}
