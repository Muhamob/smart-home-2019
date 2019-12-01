package ru.sbt.mipt.oop.devices.alarm;

public class AlarmActivated implements AlarmState {
    private transient Alarm alarm;

    public AlarmActivated(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        // Логично, что если кто-то попытается ещё раз активировать сигнализацию
        // с неправильным паролем, то здесь что-то не так, поэтому активируем сигнализацию
        if (!alarm.checkCode(code)) {
            activateAlert();
        }
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
