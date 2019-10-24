package ru.sbt.mipt.oop.eventHandlers;

import ru.sbt.mipt.oop.SensorEvent;
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
    ALARM_ACTION {
        @Override
        public HomeComponentAction getAction(SensorEvent event) {
            return new AlarmAction(event);
        }
    },
    ACTION_DOOR {
        @Override
        public HomeComponentAction getAction(SensorEvent event) {
            return new DoorScenario(event);
        }
    },
    ACTION_LIGHT {
        @Override
        public HomeComponentAction getAction(SensorEvent event) {
            return new SwitchLightById(event);
        }
    };

    public abstract HomeComponentAction getAction(SensorEvent event);
}
