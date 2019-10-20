package ru.sbt.mipt.oop.devices;

/*
Интерфейс, используемый в шаблоне Состояние
 */
public abstract class AlarmState {
    private String code;

    public AlarmState(String code) {
        this.code = code;
    }

    // package private
    String getCode() {
        return code;
    }

    AlarmState deactivate(String code) {
        if (this.code == null) return this;

        if (!getCode().equals(code)) {
            return invokeAlert();
        } else {
            return new AlarmDeactivated(null);
        }
    }

    AlarmState activate(String code) {
        return new AlarmActivated(code);
    };

    AlarmState invokeAlert() {
        return new AlarmAlert(getCode());
    }
}
