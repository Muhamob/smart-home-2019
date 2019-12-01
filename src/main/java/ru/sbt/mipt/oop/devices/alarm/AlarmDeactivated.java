package ru.sbt.mipt.oop.devices.alarm;

public class AlarmDeactivated implements AlarmState {
    private transient Alarm alarm;

    public AlarmDeactivated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        this.alarm.setCode(code);
        this.alarm.setAlarmState(new AlarmActivated(this.alarm));
    }

    @Override
    public void deactivate(String code) {
        // do nothing
    }

    @Override
    public void activateAlert() {
        this.alarm.setAlarmState(new AlarmAlert(this.alarm));
    }
}
