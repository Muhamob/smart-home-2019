package ru.sbt.mipt.oop.sources;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SensorEventType;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


/*
Источник событий для проверки действий при закрытии двери в комнате
с названием hall при стандартной конфигурации дома, взятой из класса
HomeBuilder
 */
public class TestSourceHallDoorClosing implements EventSource {

    List<SensorEvent> events = Arrays.asList(
            new SensorEvent(SensorEventType.DOOR_OPEN, "5"), // open door in barhroom
            new SensorEvent(SensorEventType.LIGHT_ON, "4"), // turn on light in bathroom
            new SensorEvent(SensorEventType.LIGHT_ON, "10"), // turn on lights in hall
            new SensorEvent(SensorEventType.LIGHT_ON, "11"),
            new SensorEvent(SensorEventType.DOOR_CLOSED, "13") // close door in hall
    );

    ListIterator<SensorEvent> iterator = events.listIterator();

    @Override
    public SensorEvent getNextSensorEvent() {
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
}
