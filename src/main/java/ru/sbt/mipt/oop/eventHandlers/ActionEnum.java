package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.actions.DoorScenario;
import ru.sbt.mipt.oop.actions.HomeComponentAction;
import ru.sbt.mipt.oop.actions.SwitchLightById;

/*
Конфигурационный код
Чтобы добавить ещё одно действие нужно дописать ACTION_SMTH { @Override HomeComp.... };
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
    },
    ALARM_ACTION {
        @Override
        public HomeComponentAction getAction(SensorEventType eventType, String id) {
            return new AlarmAction(eventType);
        }
    };

    public abstract HomeComponentAction getAction(SensorEventType eventType, String id);
}
