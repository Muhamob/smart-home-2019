package ru.sbt.mipt.oop.devices.alarm;

public class AlarmAlert implements AlarmState {
    private transient Alarm alarm;

    public AlarmAlert(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        // do nothing
    }

    @Override
    public void deactivate(String code) {
        if (alarm.checkCode(code)) {
            alarm.setAlarmState(new AlarmDeactivated(alarm));
        }
    }

    @Override
    public void activateAlert() {
        // do nothing
    }
}
