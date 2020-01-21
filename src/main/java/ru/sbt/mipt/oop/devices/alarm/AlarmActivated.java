package ru.sbt.mipt.oop.devices.alarm;

public class AlarmActivated implements AlarmState {
    private transient Alarm alarm;

    public AlarmActivated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (!alarm.checkCode(code)) {
            activateAlert();
        } else {
            alarm.setAlarmState(new AlarmDeactivated(alarm));
        }
    }

    @Override
    public void activateAlert() {
        alarm.setAlarmState(new AlarmAlert(alarm));
    }
}
