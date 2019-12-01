package ru.sbt.mipt.oop.sources;

import ru.sbt.mipt.oop.SensorEvent;

/*
Интерфейс для различный источников событий
Ими могут быть:
    1. Источник случайных событий. Тот, что был изначально
    2. Выдуманная последовательность событий. Используется для тестов
    3. Какие-то реальные источники событий
 */
public interface EventSource {
    SensorEvent getNextSensorEvent();
}
