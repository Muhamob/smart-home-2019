package ru.sbt.mipt.oop.adapters;

// Адаптер из CCSensorEvent -> SensorEvent

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

public interface SensorEventConverter {
    SensorEvent convert(CCSensorEvent event);
}
