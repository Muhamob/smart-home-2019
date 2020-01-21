package ru.sbt.mipt.oop.devices.alarm;

/*
Интерфейс, используемый в шаблоне Состояние
 */
public interface AlarmState {
    void activate(String code);
    void deactivate(String code);
    void activateAlert();
}
