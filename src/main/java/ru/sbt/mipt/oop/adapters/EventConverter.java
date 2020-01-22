package ru.sbt.mipt.oop.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

import java.util.List;

public class EventConverter implements SensorEventConverter {
    private final List<SensorEventConverter> converters;

    public EventConverter(List<SensorEventConverter> converters) {
        this.converters = converters;
    }

    @Override
    public SensorEvent convert(CCSensorEvent event) {
        SensorEvent sensorEvent = null;
        for (SensorEventConverter converter : converters) {
            if ((sensorEvent = converter.convert(event)) != null) {
                break;
            }
        }
        return sensorEvent;
    }
}
