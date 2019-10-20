package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.actions.DoorScenario;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.actions.SwitchLightById;

/*
Конфигурационный код
Чтобы добавить ещё одно событие нужно дописать EVENTSMTH(new EventSmth());
 */
public enum ActionEnum {
    ACTION_DOOR {
        @Override
        public HomeComponentAction getAction(SensorEventType eventType, String id) {
            return new DoorScenario(eventType, id);
        }
    },
    ACTION_LIGHT {
        @Override
        public HomeComponentAction getAction(SensorEventType eventType, String id) {
            return new SwitchLightById(eventType, id);
        }
    };

    public abstract HomeComponentAction getAction(SensorEventType eventType, String id);
}
