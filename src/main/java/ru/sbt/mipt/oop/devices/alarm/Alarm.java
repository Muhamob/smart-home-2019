package ru.sbt.mipt.oop.devices.alarm;

import ru.sbt.mipt.oop.devices.SmartDevice;

public class Alarm extends SmartDevice {
    private AlarmState alarmState;
    private String code;

    public Alarm(String id) {
        super(id);
        alarmState = new AlarmDeactivated(this);
    }

    void setCode(String code) {
        this.code = code;
    }

    void setAlarmState(AlarmState state) {
        this.alarmState = state;
    }

    public boolean checkCode(String code) {
        return this.code.equals(code);
    }

    public void activate(String code) {
        this.alarmState.activate(code);
    }

    public void deactivate(String code) {
        this.alarmState.deactivate(code);
    }

    public void invokeAlarm() {
        this.alarmState.activateAlert();
    }

    public AlarmState getAlarmState() {
        return this.alarmState;
    }
}
